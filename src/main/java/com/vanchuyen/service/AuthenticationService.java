package com.vanchuyen.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.vanchuyen.dto.request.AuthenticationRequest;
import com.vanchuyen.dto.request.IntrospectRequest;
import com.vanchuyen.dto.request.LogoutRequest;
import com.vanchuyen.dto.request.RefreshRequest;
import com.vanchuyen.dto.response.AuthenticationResponse;
import com.vanchuyen.dto.response.IntrospectResponse;
import com.vanchuyen.entity.InvalidatedToken;
import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.exception.InvalidateRepository;
import com.vanchuyen.repository.NguoiDungRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    NguoiDungRepository nguoiDungRepository;
    InvalidateRepository invalidateRepository;
    PasswordEncoder passwordEncoder;

    
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.vaild-duration}")
    protected Long VAILD_DURATION;

    @NonFinal
    @Value("${jwt.refresh-duration}")
    protected Long REFRESH_DURATION;

     public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = nguoiDungRepository
                .findByTaiKhoan(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getMatKhau());
        if (!isMatch) throw new AppException(ErrorCode.AUTHENTICATION_FAILED);
        var token = generateToken(user);
        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }
    String generateToken(NguoiDung user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(user.getTaiKhoan())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VAILD_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                // .claim("scope", user.getVaiTro())
                .build();
        Payload payload = new Payload(claims.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();

        } catch (JOSEException e) {

            log.error("Error while generating token", e);
            throw new RuntimeException(e);
        }
    }
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean inValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            inValid = false;
        }
        return IntrospectResponse.builder().valid(inValid).build();
    }
    public void logOut(LogoutRequest token) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(token.getToken(), true);
            String jwti = signToken.getJWTClaimsSet().getJWTID();
            Date issueTime = signToken.getJWTClaimsSet().getIssueTime();
            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jwti).expiryTime(issueTime).build();
            invalidateRepository.save(invalidatedToken);
        } catch (AppException e) {
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        // kiểm tra hiệu lực
        var signedJWT = verifyToken(request.getToken(), true);
        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();
        invalidateRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = nguoiDungRepository
                .findByTaiKhoan(username)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        var token = generateToken(user);
        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    
    SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                        .getJWTClaimsSet()
                        .getExpirationTime()
                        .toInstant()
                        .plus(REFRESH_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        if (!(verified && expiryTime.after(new Date()))) {

            throw new AppException((isRefresh) ? ErrorCode.TOKEN_EXPIRED : ErrorCode.AUTHENTICATION_FAILED);
        }
        if (invalidateRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        }
        ;
        return signedJWT;
    }
}
