package com.vanchuyen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.DuyetLichTrinhRequest;
import com.vanchuyen.dto.request.LichTrinhCreateRequest;
import com.vanchuyen.dto.response.LichTrinhResponse;
import com.vanchuyen.entity.LichTrinh;
import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.entity.TaiXe;
import com.vanchuyen.entity.TuyenDuong;
import com.vanchuyen.entity.Xe;
import com.vanchuyen.enums.TrangThaiDuyetLichTrinh;
import com.vanchuyen.enums.TrangThaiVanChuyen;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.mapper.LichTrinhMapper;
import com.vanchuyen.repository.LichTrinhRepository;
import com.vanchuyen.repository.TaiXeRepository;
import com.vanchuyen.repository.TuyenDuongRepository;
import com.vanchuyen.repository.XeRepository;

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
public class LichTrinhService {
    LichTrinhRepository lichTrinhRepository;
    LichTrinhMapper lichTrinhMapper;
    TuyenDuongRepository tuyenDuongRepository;
    TaiXeRepository taiXeRepository;
    XeRepository xeRepository;
    NguoiDungService userService; 


    public Page<LichTrinhResponse> getAll(String maChuyen, Integer trangThai, Integer page, Integer size) {
        int defaultPage = (page != null && page >= 0) ? page : 0;
        int defaultSize = (size != null && size > 0) ? size : 20;        // Mặc định 20 bản ghi/trang
        int maxSize = 100;                                              // Giới hạn max
        defaultSize = Math.min(defaultSize, maxSize);
        Pageable pageable = PageRequest.of(defaultPage, defaultSize, Sort.by("createdAt").descending());
        Page<LichTrinh> lichTrinhPage;

        if (maChuyen != null && trangThai != null) {
            lichTrinhPage = lichTrinhRepository.findByMaChuyenContainingIgnoreCaseAndTrangThai(maChuyen, trangThai, pageable);
        } else if (maChuyen != null) {
            lichTrinhPage = lichTrinhRepository.findByMaChuyenContainingIgnoreCase(maChuyen, pageable);
        } else if (trangThai != null) {
            lichTrinhPage = lichTrinhRepository.findByTrangThai(trangThai, pageable);
        } else {
            lichTrinhPage = lichTrinhRepository.findAll(pageable);
        }

        List<LichTrinhResponse> content = lichTrinhPage.stream()
                .map(lichTrinhMapper::toResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageable, lichTrinhPage.getTotalElements());
    }
    public LichTrinhResponse getById(Integer id) {
        LichTrinh lichTrinh = lichTrinhRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LICH_TRINH_NOT_FOUND));
        return lichTrinhMapper.toResponse(lichTrinh);
    }
    public LichTrinhResponse createLichTrinh(LichTrinhCreateRequest request) {
        NguoiDung nguoiTao = userService.getMyInfoByToken();

        // Lấy các entity liên quan
        TuyenDuong tuyenDuong = tuyenDuongRepository.findById(request.getTuyenDuongId())
                .orElseThrow(() -> new AppException(ErrorCode.TUYEN_DUONG_NOT_FOUND));

        Xe xe = xeRepository.findById(request.getXeId())
                .orElseThrow(() -> new AppException(ErrorCode.XE_NOT_FOUND));
        if (!Boolean.TRUE.equals(xe.getTrangThaiHoatDong())) {
            throw new AppException(ErrorCode.XE_DANG_DUNG);
        }
        Long daCoLichTrinhTrung = lichTrinhRepository.countLichTrinhTrung(
                xe.getId(),
                request.getNgayKhoiHanh(),
                request.getNgayDuKienDen()
        );
        if (daCoLichTrinhTrung > 0) {
            throw new AppException(ErrorCode.XE_DA_CO_LICH_TRINH_TRONG_KHOANG_THOI_GIAN_NAY);
        }
        
        TaiXe taiXeChinh = taiXeRepository.findById(xe.getTaiXeChinh().getId())
                .orElseThrow(() -> new AppException(ErrorCode.TAI_XE_NOT_FOUND));
        if (taiXeChinh.getTrangThaiLamViec() != 0) {
            throw new AppException(ErrorCode.TAI_XE_DANG_DUNG);
            
        }

        // Tạo entity
        LichTrinh lichTrinh = new LichTrinh();
        lichTrinh.setMaChuyen(taoMaLichTrinh());
        lichTrinh.setTuyenDuong(tuyenDuong);
        lichTrinh.setXe(xe);
        lichTrinh.setTaiXeChinh(taiXeChinh);
        lichTrinh.setNgayKhoiHanh(request.getNgayKhoiHanh());
        lichTrinh.setNgayDuKienDen(request.getNgayDuKienDen());
        lichTrinh.setHangHoaMoTa(request.getHangHoaMoTa());
        lichTrinh.setTrongLuongHang(request.getTrongLuongHang());
        lichTrinh.setNguoiTao(nguoiTao);

        
        lichTrinh.setTrangThai(0); 
        lichTrinh.setTrangThaiDuyet(0);

        LichTrinh saved = lichTrinhRepository.save(lichTrinh);

        return lichTrinhMapper.toResponse(saved);
    }
     public LichTrinhResponse update(Integer id, LichTrinhCreateRequest request) {
        LichTrinh lichTrinh = lichTrinhRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LICH_TRINH_NOT_FOUND));

        TuyenDuong tuyenDuong = tuyenDuongRepository.findById(request.getTuyenDuongId())
                .orElseThrow(() -> new AppException(ErrorCode.TUYEN_DUONG_NOT_FOUND));

        Xe xe = xeRepository.findById(request.getXeId())
                .orElseThrow(() -> new AppException(ErrorCode.XE_NOT_FOUND));

        TaiXe taiXeChinh = taiXeRepository.findById(xe.getTaiXeChinh().getId())
                .orElseThrow(() -> new AppException(ErrorCode.TAI_XE_NOT_FOUND));

       

        lichTrinh.setTuyenDuong(tuyenDuong);
        lichTrinh.setXe(xe);
        lichTrinh.setTaiXeChinh(taiXeChinh);
        lichTrinh.setNgayKhoiHanh(request.getNgayKhoiHanh());
        lichTrinh.setNgayDuKienDen(request.getNgayDuKienDen());
        lichTrinh.setHangHoaMoTa(request.getHangHoaMoTa());
        lichTrinh.setTrongLuongHang(request.getTrongLuongHang());

        lichTrinhRepository.save(lichTrinh);

        return lichTrinhMapper.toResponse(lichTrinh);
    }
    public LichTrinhResponse pheDuyet(Integer id, DuyetLichTrinhRequest request) {
        LichTrinh lichTrinh = lichTrinhRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LICH_TRINH_NOT_FOUND));

        NguoiDung nguoiDuyet = userService.getMyInfoByToken();
        lichTrinh.setNguoiDuyet(nguoiDuyet);
        lichTrinh.setThoiGianDuyet(LocalDateTime.now());

        TrangThaiVanChuyen trangThaiHienTai = TrangThaiVanChuyen.fromCode(lichTrinh.getTrangThai());
        if (trangThaiHienTai != TrangThaiVanChuyen.DA_DEN_NOI) {
            throw new AppException(ErrorCode.LICH_TRINH_CHUA_DEN_NOI);
        }
        if (lichTrinh.getTrangThaiDuyet() != null &&
            !lichTrinh.getTrangThaiDuyet().equals(TrangThaiDuyetLichTrinh.CHO_DUYET.getCode())) {
            throw new AppException(ErrorCode.LICH_TRINH_DA_DUYET);
        }
        if (Boolean.TRUE.equals(request.getDuyet())) {
            lichTrinh.setTrangThaiDuyet(TrangThaiDuyetLichTrinh.DA_DUYET.getCode());
        } else {
            lichTrinh.setTrangThaiDuyet(TrangThaiDuyetLichTrinh.TU_CHOI.getCode());
        }
        lichTrinh.setUpdatedAt(LocalDateTime.now());
        lichTrinhRepository.save(lichTrinh);
        return lichTrinhMapper.toResponse(lichTrinh);
    }
    public LichTrinhResponse capNhatTrangThaiVanChuyen(Integer id, TrangThaiVanChuyen trangThaiMoi) {
        LichTrinh lichTrinh = lichTrinhRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LICH_TRINH_NOT_FOUND));

        TrangThaiVanChuyen trangThaiHienTai = TrangThaiVanChuyen.fromCode(lichTrinh.getTrangThai());

        // Quy tắc chuyển trạng thái hợp lệ
        switch (trangThaiMoi) {
            case DANG_VAN_CHUYEN -> {
                if (trangThaiHienTai != TrangThaiVanChuyen.CHUA_KHOI_HANH &&
                    trangThaiHienTai != TrangThaiVanChuyen.TAM_DUNG) {
                    throw new AppException(ErrorCode.TRANG_THAI_KHONG_HOP_LE);
                }
            }
            case TAM_DUNG -> {
                if (trangThaiHienTai != TrangThaiVanChuyen.DANG_VAN_CHUYEN) {
                    throw new AppException(ErrorCode.TRANG_THAI_KHONG_HOP_LE);
                }
            }
            case DA_DEN_NOI -> {
                if (trangThaiHienTai != TrangThaiVanChuyen.DANG_VAN_CHUYEN) {
                    throw new AppException(ErrorCode.TRANG_THAI_KHONG_HOP_LE);
                }
                lichTrinh.setNgayThucTeDen(LocalDateTime.now());
            }
            case HUY_CHUYEN -> {
                if (trangThaiHienTai == TrangThaiVanChuyen.DA_DEN_NOI) {
                    throw new AppException(ErrorCode.KHONG_THE_HUY_CHUYEN_DA_HOAN_THANH);
                }
            }
            default -> {
                // cho phép CHUA_KHOI_HANH mà không check gì
            }
        }

        lichTrinh.setTrangThai(trangThaiMoi.getCode());
        lichTrinhRepository.save(lichTrinh);

        return lichTrinhMapper.toResponse(lichTrinh);
    }

    private String taoMaLichTrinh() {
        String prefix = "CHUYEN";
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(8);
        int random = new Random().nextInt(1000);
        return String.format("%s%s%03d", prefix, timestamp, random);
    }
    
}
