package com.vanchuyen.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.NguoiDungCreateRequest;
import com.vanchuyen.dto.request.TaiXeRequest;
import com.vanchuyen.dto.response.NguoiDungResponse;
import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.entity.TaiXe;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.mapper.NguoiDungMapper;
import com.vanchuyen.mapper.TaiXeMapper;
import com.vanchuyen.repository.NguoiDungRepository;
import com.vanchuyen.repository.TaiXeRepository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
@Transactional
@Slf4j
public class NguoiDungService {
    NguoiDungRepository nguoiDungRepository;
    NguoiDungMapper nguoiDungMapper;
    PasswordEncoder passwordEncoder;
    TaiXeRepository taiXeRepository;
    TaiXeMapper taiXeMapper;
    public List<NguoiDungResponse> getAll() {
        // return nguoiDungRepository.findAll();
        return nguoiDungRepository.findAll()
                .stream()
                .map(nguoiDungMapper::toResponse)
                .collect(Collectors.toList());
    }
    public NguoiDungResponse create(NguoiDungCreateRequest request) {
        // Kiểm tra tài khoản đã tồn tại
        if (nguoiDungRepository.existsByTaiKhoan(request.getTaiKhoan())) {
            throw new AppException(ErrorCode.NGUOI_DUNG_ALREADY_EXISTS);
        }
        NguoiDung nguoiDung = nguoiDungMapper.toCreateEntity(request);
        nguoiDung.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        nguoiDung.setTrangThai(true);
        nguoiDung = nguoiDungRepository.save(nguoiDung);
         // Nếu vai trò là tài xế, tạo thêm thông tin tài xế
        if ("TAI_XE".equalsIgnoreCase(request.getVaiTro())) {
            if (request.getThongTinTaiXe() == null) {
                throw new AppException(ErrorCode.THONG_TIN_TAI_XE_REQUIRED);
            }
            
            TaiXeRequest ttTaiXe = request.getThongTinTaiXe();
            TaiXe taiXe = taiXeMapper.toEntity(ttTaiXe);
            taiXe.setNguoiDung(nguoiDung);
            taiXe.setNguoiDung(nguoiDung);
            taiXe.setMaTaiXe(taoMaTaiXe()); 
            taiXe.setTrangThaiLamViec(request.getThongTinTaiXe().getTrangThaiLamViec().getCode());
            log.info("Ma tai xe: {}", taiXe);
            taiXe = taiXeRepository.save(taiXe);

            nguoiDung.setTaiXe(taiXe);
            
        }
        return nguoiDungMapper.toResponse(nguoiDung);
    }
    public NguoiDungResponse update(Integer id, NguoiDungCreateRequest request) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        if (nguoiDungRepository.existsByTaiKhoanAndIdNot(request.getTaiKhoan(), id)) {
            throw new AppException(ErrorCode.NGUOI_DUNG_EXISTED);
        }
        nguoiDungMapper.updateFromRequest(request, nguoiDung);
        if (request.getMatKhau() != null && !request.getMatKhau().isBlank()) {
            nguoiDung.setMatKhau(passwordEncoder.encode(request.getMatKhau()));
        }
         // Nếu vai trò là tài xế, tạo thêm thông tin tài xế
        if ("TAI_XE".equalsIgnoreCase(request.getVaiTro()) || "TAI_XE".equalsIgnoreCase(nguoiDung.getVaiTro())) {
            TaiXeRequest ttTaiXe = request.getThongTinTaiXe();
            
            if (ttTaiXe  == null) {
                throw new AppException(ErrorCode.THONG_TIN_TAI_XE_REQUIRED);
            }
            
            TaiXe taiXe = nguoiDung.getTaiXe();
            if (taiXe == null) {
                taiXe = taiXeMapper.toEntity(ttTaiXe);
                taiXe.setNguoiDung(nguoiDung);
                taiXe.setMaTaiXe(taoMaTaiXe());
            } 
            // Nếu đã có bản ghi tài xế → cập nhật thông tin
            else {
                taiXeMapper.updateFromRequest(ttTaiXe, taiXe);
            }
            log.info("Ma tai xe: {}", taiXe);
            taiXe = taiXeRepository.save(taiXe);
            
            nguoiDung.setTaiXe(taiXe);
            
        }else {
            if (nguoiDung.getTaiXe() != null) {
                taiXeRepository.delete(nguoiDung.getTaiXe());
                nguoiDung.setTaiXe(null);
            }
        }
        nguoiDung = nguoiDungRepository.save(nguoiDung);
        return nguoiDungMapper.toResponse(nguoiDung);
    }
    public void deleted(Integer id){
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        if (nguoiDung.getTaiXe() != null) {
            taiXeRepository.delete(nguoiDung.getTaiXe());
        }
        nguoiDungRepository.deleteById(id);
    }
    public NguoiDungResponse getById(Integer id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        return nguoiDungMapper.toResponse(nguoiDung);
    }
    public NguoiDungResponse getMyInfo() {
        var conText = SecurityContextHolder.getContext();
        String name =  conText.getAuthentication().getName();
        NguoiDung nguoiDung = nguoiDungRepository.findByTaiKhoan(name)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        return nguoiDungMapper.toResponse(nguoiDung);
    }
    public NguoiDung getMyInfoByToken() {
        var conText = SecurityContextHolder.getContext();
        String name =  conText.getAuthentication().getName();
        NguoiDung nguoiDung = nguoiDungRepository.findByTaiKhoan(name)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        return nguoiDung;
    }
    public NguoiDung getNguoiDungById(Integer id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NGUOI_DUNG_NOT_FOUND));
        return nguoiDung;
    }
    // Hàm tạo mã tài xế tự động
    private String taoMaTaiXe() {
        String prefix = "TX";
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(8);
        int random = new Random().nextInt(1000);
        return String.format("%s%s%03d", prefix, timestamp, random);
    }
    

}
