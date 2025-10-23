package com.vanchuyen.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vanchuyen.dto.request.TuyenDuongCreateRequest;
import com.vanchuyen.dto.response.TuyenDuongResponse;
import com.vanchuyen.entity.TuyenDuong;
import com.vanchuyen.mapper.TuyenDuongMapper;
import com.vanchuyen.repository.TuyenDuongRepository;

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
public class TuyenDuongService {
    TuyenDuongRepository tuyenDuongRepository;
    TuyenDuongMapper mapper;
    // Get all
    public List<TuyenDuongResponse> getAllTuyenDuong() {
        return tuyenDuongRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
    public TuyenDuongResponse createTuyenDuong(TuyenDuongCreateRequest request) {

        TuyenDuong entity = mapper.toEntity(request);
        if (request.getTrangThai() != null) {
            entity.setTrangThai(request.getTrangThai().getCode());
        }

        TuyenDuong saved = tuyenDuongRepository.save(entity);
        return mapper.toResponse(saved);
    }
}
