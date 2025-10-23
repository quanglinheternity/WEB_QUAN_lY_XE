package com.vanchuyen.controller;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanchuyen.dto.request.CapNhatTrangThaiVanChuyenRequest;
import com.vanchuyen.dto.request.DuyetLichTrinhRequest;
import com.vanchuyen.dto.request.LichTrinhCreateRequest;
import com.vanchuyen.dto.response.ApiResponse;
import com.vanchuyen.dto.response.LichTrinhResponse;
import com.vanchuyen.service.LichTrinhService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/lich-trinh")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LichTrinhController {
    LichTrinhService lichTrinhService;
    @GetMapping
    public ApiResponse<Page<LichTrinhResponse>> getAll(
            @RequestParam(required = false) String maChuyen,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ApiResponse.<Page<LichTrinhResponse>>builder()
                .data(lichTrinhService.getAll(maChuyen, trangThai, page, size))
                .build();
    }
    @GetMapping("/{id}")
    public LichTrinhResponse getById(@PathVariable Integer id) {
        return lichTrinhService.getById(id);
    }
    @PostMapping
    public ApiResponse<LichTrinhResponse> create(@RequestBody @Valid LichTrinhCreateRequest request) {
        return ApiResponse.<LichTrinhResponse>builder()
                .data(lichTrinhService.createLichTrinh(request))
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<LichTrinhResponse> update(@PathVariable Integer id, @RequestBody @Valid LichTrinhCreateRequest request) {
        return ApiResponse.<LichTrinhResponse>builder()
                .data(lichTrinhService.update(id, request))
                .build();
    }
    @PutMapping("/{id}/phe-duyet")
    public ApiResponse<LichTrinhResponse> pheDuyet(@PathVariable Integer id, @RequestBody @Valid DuyetLichTrinhRequest request) {
        return ApiResponse.<LichTrinhResponse>builder()
                .data(lichTrinhService.pheDuyet(id, request))
                .build();
    }
    @PutMapping("/{id}/trang-thai-van-chuyen")
    public ApiResponse<LichTrinhResponse> capNhatTrangThaiVanChuyen(
            @PathVariable Integer id,
            @Valid @RequestBody CapNhatTrangThaiVanChuyenRequest request
    ) {
        return ApiResponse.<LichTrinhResponse>builder()
                .data(lichTrinhService.capNhatTrangThaiVanChuyen(id, request.getTrangThaiMoi()))
                .build();
    }
}
