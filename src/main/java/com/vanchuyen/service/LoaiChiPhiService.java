package com.vanchuyen.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.LoaiChiPhiRequest;
import com.vanchuyen.dto.response.LoaiChiPhiResponse;
import com.vanchuyen.entity.LoaiChiPhi;
import com.vanchuyen.enums.NhomLoaiChiPhi;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.mapper.LoaiChiPhiMapper;
import com.vanchuyen.repository.LoaiChiPhiRepository;

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
public class LoaiChiPhiService {
    LoaiChiPhiRepository loaiChiPhiRepository;
    LoaiChiPhiMapper loaiChiPhiMapper;
    public LoaiChiPhiResponse create(LoaiChiPhiRequest request) {
        

        LoaiChiPhi entity = loaiChiPhiMapper.toEntity(request);
        try {
            entity.setNhomChiPhi(NhomLoaiChiPhi.fromCode(request.getNhomChiPhi()));
        } catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_DATA);
        }
        entity.setMaLoaiChiPhi(taoMaLoaiChiPhi());
        loaiChiPhiRepository.save(entity);

        return loaiChiPhiMapper.toResponse(entity);
    }

    public LoaiChiPhiResponse update(Integer id, LoaiChiPhiRequest request) {
        LoaiChiPhi entity = loaiChiPhiRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LOAI_CHI_PHI_NOT_FOUND));

        loaiChiPhiMapper.updateEntity(entity, request);
        try {
            entity.setNhomChiPhi(NhomLoaiChiPhi.fromCode(request.getNhomChiPhi()));
        } catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_DATA);
        }
        loaiChiPhiRepository.save(entity);

        return loaiChiPhiMapper.toResponse(entity);
    }
    public LoaiChiPhiResponse updateTrangThai(Integer id) {
        LoaiChiPhi entity = loaiChiPhiRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LOAI_CHI_PHI_NOT_FOUND));

        if (entity.getTrangThai()) {
            entity.setTrangThai(false);
        } else {
            entity.setTrangThai(true);
        }
        loaiChiPhiRepository.save(entity);
        return loaiChiPhiMapper.toResponse(entity);
    }
    public List<LoaiChiPhiResponse> getAll() {
        return loaiChiPhiRepository.findAll()
                .stream()
                .map(loaiChiPhiMapper::toResponse)
                .collect(Collectors.toList());
    }

    public LoaiChiPhiResponse getById(Integer id) {
        LoaiChiPhi entity = loaiChiPhiRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LOAI_CHI_PHI_NOT_FOUND));
        return loaiChiPhiMapper.toResponse(entity);
    }

    public void delete(Integer id) {
        LoaiChiPhi entity = loaiChiPhiRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LOAI_CHI_PHI_NOT_FOUND));
        if (entity.getYeuCauChiPhis() != null && !entity.getYeuCauChiPhis().isEmpty()) {
            throw new AppException(ErrorCode.LOAI_CHI_PHI_CON_YEU_CAU_CHI_PHI);
        }
        loaiChiPhiRepository.delete(entity);
    }
    private String taoMaLoaiChiPhi() {
        String prefix = "CP";
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(8);
        int random = new Random().nextInt(1000);
        return String.format("%s%s%03d", prefix, timestamp, random);
    }
}
