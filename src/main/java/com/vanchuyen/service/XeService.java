package com.vanchuyen.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.XeCreateRequest;
import com.vanchuyen.dto.request.XeUpdateRequest;
import com.vanchuyen.dto.response.XeResponse;
import com.vanchuyen.dto.response.XeResponsedetail;
import com.vanchuyen.entity.LoaiXe;
import com.vanchuyen.entity.TaiXe;
import com.vanchuyen.entity.Xe;
import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;
import com.vanchuyen.mapper.XeMapper;
import com.vanchuyen.repository.LoaiXeRepository;
import com.vanchuyen.repository.TaiXeRepository;
import com.vanchuyen.repository.XeRepository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
@Transactional
public class XeService {
    XeRepository xeRepository;
    LoaiXeRepository loaiXeRepository;
    TaiXeRepository taiXeRepository;
    XeMapper xeMapper;
    public List<XeResponse> getAll() {
        return xeRepository.findAll()
                .stream()
                .map(xeMapper::toXeResponse)
                .collect(Collectors.toList());
    }
    public XeResponse create(XeCreateRequest req) {
        if (xeRepository.existsByBienSoXe(req.getBienSoXe())) {
            throw new AppException(ErrorCode.XE_EXISTED);
        }
        Xe xe = xeMapper.toXeCreateRequest(req);

        LoaiXe loaiXe = loaiXeRepository.findById(req.getLoaiXeId())
                .orElseThrow(() -> new AppException(ErrorCode.LOAI_XE_NOT_FOUND));
        xe.setLoaiXe(loaiXe);

        if (req.getTaiXeChinhId() != null) {
            TaiXe taiXe = taiXeRepository.findById(req.getTaiXeChinhId())
                    .orElseThrow(() -> new AppException(ErrorCode.TAI_XE_NOT_FOUND));
            xe.setTaiXeChinh(taiXe);
        }

        xeRepository.save(xe);
        return xeMapper.toXeResponse(xe);
    }

    public XeResponse update(Integer id, XeUpdateRequest req) {
        Xe xe = xeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.XE_NOT_FOUND));

        xeMapper.updateXeFromUpdateRequest(req, xe);
        xeRepository.save(xe);

        return xeMapper.toXeResponse(xe);
    }
    public XeResponsedetail getById(Integer id) {
        Xe xe = xeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.XE_NOT_FOUND));
        return xeMapper.toXeResponsedetail(xe);
    }
    public String updateByTrangThai(Integer id) {
        Xe xe = xeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.XE_NOT_FOUND));
        String message = "";
        if (xe.getTrangThaiHoatDong()) {
            xe.setTrangThaiHoatDong(false);
            message = "Xe đã dừng hoạt động";
        } else {
            xe.setTrangThaiHoatDong(true);
            message = "Xe đã hoạt động";
        }
        xeRepository.save(xe);
        return message;
    }

    public void delete(Integer id) {
        if (!xeRepository.existsById(id)) throw new AppException(ErrorCode.XE_NOT_FOUND);
        xeRepository.deleteById(id);
    }
    
}
