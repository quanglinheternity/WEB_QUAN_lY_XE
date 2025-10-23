package com.vanchuyen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.DuyetYeuCauRequest;
import com.vanchuyen.dto.request.YeuCauChiPhiRequest;
import com.vanchuyen.dto.response.YeuCauChiPhiResponse;
import com.vanchuyen.entity.LichSuBaoDuong;
import com.vanchuyen.entity.LichTrinh;
import com.vanchuyen.entity.LoaiChiPhi;
import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.entity.YeuCauChiPhi;
import com.vanchuyen.enums.NhomLoaiChiPhi;
import com.vanchuyen.enums.TrangThaiYeuCauChiPhi;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.mapper.YeuCauChiPhiMapper;
import com.vanchuyen.repository.LichSuBaoDuongRepository;
import com.vanchuyen.repository.LichTrinhRepository;
import com.vanchuyen.repository.LoaiChiPhiRepository;
import com.vanchuyen.repository.YeuCauChiPhiRepository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
@Transactional
public class YeuCauChiPhiService {
    YeuCauChiPhiRepository yeuCauChiPhiRepository;
    YeuCauChiPhiMapper yeuCauChiPhiMapper;
    NguoiDungService nguoiDungService;
    LichTrinhRepository lichTrinhRepository;
    LoaiChiPhiRepository loaiChiPhiRepository;
    LichSuBaoDuongRepository lichSuBaoDuongRepository;
    public YeuCauChiPhiResponse create(YeuCauChiPhiRequest request) {
       
        NguoiDung nguoiDung = nguoiDungService.getMyInfoByToken();
        long count = yeuCauChiPhiRepository.count() + 1;
        String maYeuCau = String.format("YCCP%05d", count);

        YeuCauChiPhi entity = yeuCauChiPhiMapper.toEntity(request);
        LichTrinh lichTrinh = lichTrinhRepository.findById(request.getLichTrinhId())
            .orElseThrow(() -> new AppException(ErrorCode.LICH_TRINH_NOT_FOUND));
        LoaiChiPhi loaiChiPhi = loaiChiPhiRepository.findById(request.getLoaiChiPhiId())
            .orElseThrow(() -> new AppException(ErrorCode.LOAI_CHI_PHI_NOT_FOUND));
        entity.setLichTrinh(lichTrinh);
        entity.setLoaiChiPhi(loaiChiPhi);
        entity.setMaYeuCau(maYeuCau);
        entity.setTrangThai(0); 
        entity.setNguoiGui(nguoiDung);

        yeuCauChiPhiRepository.save(entity);
        YeuCauChiPhi fullEntity = yeuCauChiPhiRepository.findById(entity.getId())
            .orElseThrow(() -> new AppException(ErrorCode.YEU_CAU_CHI_PHI_NOT_FOUND));
        return yeuCauChiPhiMapper.toResponse(fullEntity);
    }
    public List<YeuCauChiPhiResponse> getAll(){
        return yeuCauChiPhiRepository.findAll()
        .stream()
        .map(yeuCauChiPhiMapper::toResponse)
        .collect(Collectors.toList());
    }
    public List<YeuCauChiPhiResponse> getByNguoiGui(){
        NguoiDung nguoiDung = nguoiDungService.getMyInfoByToken();
        return yeuCauChiPhiRepository.findByNguoiGui(nguoiDung)
        .stream()
        .map(yeuCauChiPhiMapper::toResponse)
        .collect(Collectors.toList());
    }
    public YeuCauChiPhiResponse getById(Integer id){
        return yeuCauChiPhiRepository.findById(id)
        .map(yeuCauChiPhiMapper::toResponse)
        .orElseThrow(() -> new AppException(ErrorCode.YEU_CAU_CHI_PHI_NOT_FOUND));
    }
    public YeuCauChiPhiResponse update(Integer id, YeuCauChiPhiRequest request){
        YeuCauChiPhi entity = yeuCauChiPhiRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.YEU_CAU_CHI_PHI_NOT_FOUND));
        yeuCauChiPhiMapper.updateFromRequest(request, entity);
        LichTrinh lichTrinh = lichTrinhRepository.findById(request.getLichTrinhId())
            .orElseThrow(() -> new AppException(ErrorCode.LICH_TRINH_NOT_FOUND));
        LoaiChiPhi loaiChiPhi = loaiChiPhiRepository.findById(request.getLoaiChiPhiId())
            .orElseThrow(() -> new AppException(ErrorCode.LOAI_CHI_PHI_NOT_FOUND));

        entity.setLichTrinh(lichTrinh);
        entity.setLoaiChiPhi(loaiChiPhi);

        // Nếu cần cập nhật thời gian chỉnh sửa
        entity.setUpdatedAt(LocalDateTime.now());
        return yeuCauChiPhiMapper.toResponse(yeuCauChiPhiRepository.save(entity));
    }
    public void delete(Integer id){
        YeuCauChiPhi entity = yeuCauChiPhiRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.YEU_CAU_CHI_PHI_NOT_FOUND));
        yeuCauChiPhiRepository.delete(entity);
    }
    public YeuCauChiPhiResponse duyetYeuCau(Integer id, DuyetYeuCauRequest request) {
        YeuCauChiPhi entity = yeuCauChiPhiRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.YEU_CAU_CHI_PHI_NOT_FOUND));

        NguoiDung nguoiDuyet = nguoiDungService.getMyInfoByToken();
        TrangThaiYeuCauChiPhi trangThaiHienTai = TrangThaiYeuCauChiPhi.fromCode(entity.getTrangThai());

        switch (trangThaiHienTai) {
            case CHO_DUYET_QL -> {
                // Quản lý duyệt
                entity.setNguoiDuyetQL(nguoiDuyet);
                entity.setThoiGianDuyetQL(LocalDateTime.now());
                entity.setGhiChuDuyetQL(request.getGhiChu());

                if (Boolean.FALSE.equals(request.getDuyet())) {
                    entity.setTrangThai(TrangThaiYeuCauChiPhi.TU_CHOI.getCode());
                } else {
                    entity.setTrangThai(TrangThaiYeuCauChiPhi.DA_DUYET_QL.getCode());
                }
            }

            case DA_DUYET_QL -> {
                // Kế toán duyệt
                
                entity.setNguoiDuyetKT(nguoiDuyet);
                entity.setThoiGianDuyetKT(LocalDateTime.now());
                entity.setGhiChuDuyetKT(request.getGhiChu());
                entity.setTrangThai(TrangThaiYeuCauChiPhi.DA_DUYET_KT.getCode());
                if (entity.getLoaiChiPhi() != null 
                        && entity.getLoaiChiPhi().getNhomChiPhi() != null
                        && entity.getLoaiChiPhi().getNhomChiPhi() == NhomLoaiChiPhi.BAO_DUONG) {

                    LichSuBaoDuong lichSu = new LichSuBaoDuong();
                    lichSu.setXe(entity.getLichTrinh().getXe()); // xe trong lịch trình
                    lichSu.setNgayBaoDuong(entity.getNgayChiPhi());
                    lichSu.setLoaiBaoDuong(entity.getLoaiChiPhi().getTenLoaiChiPhi());
                    lichSu.setChiPhi(entity.getSoTien());
                    lichSu.setMoTaCongViec(entity.getMoTa());
                    lichSu.setDonViThucHien(entity.getDiaDiemChiPhi());
                    lichSu.setNguoiTao(entity.getNguoiGui());
                    lichSu.setCreatedAt(LocalDateTime.now());

                    lichSuBaoDuongRepository.save(lichSu);
                }
            }

            default -> throw new AppException(ErrorCode.INVALID_STATE_TRANSITION);
        }

        yeuCauChiPhiRepository.save(entity);
        return yeuCauChiPhiMapper.toResponse(entity);
    }

}
