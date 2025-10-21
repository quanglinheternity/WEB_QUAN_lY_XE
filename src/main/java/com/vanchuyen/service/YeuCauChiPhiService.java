package com.vanchuyen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.DuyetYeuCauRequest;
import com.vanchuyen.dto.request.YeuCauChiPhiRequest;
import com.vanchuyen.dto.response.YeuCauChiPhiResponse;
import com.vanchuyen.entity.LichTrinh;
import com.vanchuyen.entity.LoaiChiPhi;
import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.entity.YeuCauChiPhi;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.mapper.YeuCauChiPhiMapper;
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
    public YeuCauChiPhiResponse duyetYeuCau(Integer id, DuyetYeuCauRequest request){
        YeuCauChiPhi entity = yeuCauChiPhiRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.YEU_CAU_CHI_PHI_NOT_FOUND));
        NguoiDung nguoiDuyet = nguoiDungService.getMyInfoByToken();
        if (entity.getTrangThai() == 0) {
            entity.setNguoiDuyetQL(nguoiDuyet);
            entity.setThoiGianDuyetQL(LocalDateTime.now());
            entity.setGhiChuDuyetQL(request.getGhiChu());

            if (request.getDuyet() == false) {
                entity.setTrangThai(3);
            } else {
                entity.setTrangThai(1);
            }

        } else if (entity.getTrangThai() == 1) {
            entity.setNguoiDuyetKT(nguoiDuyet);
            entity.setThoiGianDuyetKT(LocalDateTime.now());
            entity.setGhiChuDuyetKT(request.getGhiChu());
            entity.setTrangThai(2);
        } else {
            throw new AppException(ErrorCode.INVALID_STATE_TRANSITION);
        }
        return yeuCauChiPhiMapper.toResponse(yeuCauChiPhiRepository.save(entity));
    }

}
