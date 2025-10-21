package com.vanchuyen.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.NguoiDungCreateRequest;
import com.vanchuyen.dto.response.NguoiDungResponse;
import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.mapper.NguoiDungMapper;
import com.vanchuyen.repository.NguoiDungRepository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
@Transactional
public class NguoiDungService {
    NguoiDungRepository nguoiDungRepository;
    NguoiDungMapper nguoiDungMapper;
    public List<NguoiDung> getAll() {
        return nguoiDungRepository.findAll();
    }
    public NguoiDungResponse create(NguoiDungCreateRequest request) {
        // Kiểm tra tài khoản đã tồn tại
        if (nguoiDungRepository.existsByTaiKhoan(request.getTaiKhoan())) {
            throw new AppException(ErrorCode.NGUOI_DUNG_ALREADY_EXISTS);
        }
        NguoiDung nguoiDung = nguoiDungMapper.toCreateEntity(request);
        PasswordEncoder passwordService = new BCryptPasswordEncoder(10);
        nguoiDung.setMatKhau(passwordService.encode(request.getMatKhau()));
        nguoiDung = nguoiDungRepository.save(nguoiDung);
        return nguoiDungMapper.toResponse(nguoiDung);
    }
    public NguoiDungResponse update(Integer id, NguoiDungCreateRequest request) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        if (nguoiDungRepository.existsByTaiKhoanAndIdNot(request.getTaiKhoan(), id)) {
            throw new AppException(ErrorCode.NGUOI_DUNG_EXISTED);
        }
        nguoiDungMapper.updateFromRequest(request, nguoiDung);
        PasswordEncoder passwordService = new BCryptPasswordEncoder(10);
        nguoiDung.setMatKhau(passwordService.encode(request.getMatKhau()));
        nguoiDung = nguoiDungRepository.save(nguoiDung);
        return nguoiDungMapper.toResponse(nguoiDung);
    }
    public void deleted(Integer id){
        nguoiDungRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        nguoiDungRepository.deleteById(id);
    }
    public NguoiDungResponse getById(Integer id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        return nguoiDungMapper.toResponse(nguoiDung);
    }

}
