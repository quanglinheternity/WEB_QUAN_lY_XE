package com.vanchuyen.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanchuyen.dto.request.LoaiChiPhiRequest;
import com.vanchuyen.dto.response.ApiResponse;
import com.vanchuyen.dto.response.LoaiChiPhiResponse;
import com.vanchuyen.service.LoaiChiPhiService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/loai-chi-phi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LoaiChiPhiController {
    LoaiChiPhiService loaiChiPhiService;
    @PostMapping
    public ApiResponse<LoaiChiPhiResponse> create(@Valid @RequestBody LoaiChiPhiRequest request) {
        LoaiChiPhiResponse response = loaiChiPhiService.create(request);
        return ApiResponse.<LoaiChiPhiResponse>builder()
                .data(response)
                .message("Tạo loại chi phí thành công")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<LoaiChiPhiResponse> update(@PathVariable Integer id, 
                                                  @Valid @RequestBody LoaiChiPhiRequest request) {
        LoaiChiPhiResponse response = loaiChiPhiService.update(id, request);
        return ApiResponse.<LoaiChiPhiResponse>builder()
                .data(response)
                .message("Cập nhật loại chi phí thành công")
                .build();
    }
    @PutMapping("/{id}/trang-thai")
    public ApiResponse<LoaiChiPhiResponse> updateTrangThai(@PathVariable Integer id) {
        LoaiChiPhiResponse response = loaiChiPhiService.updateTrangThai(id );
        return ApiResponse.<LoaiChiPhiResponse>builder()
                .data(response)
                .message("Cập nhật trạng thái loại chi phí thàng công")
                .build();
    }

    @GetMapping
    public ApiResponse<List<LoaiChiPhiResponse>> getAll() {
        List<LoaiChiPhiResponse> list = loaiChiPhiService.getAll();
        return ApiResponse.<List<LoaiChiPhiResponse>>builder()
                .data(list)
                .message("Lấy danh sách loại chi phí thành công")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<LoaiChiPhiResponse> getById(@PathVariable Integer id) {
        LoaiChiPhiResponse response = loaiChiPhiService.getById(id);
        return ApiResponse.<LoaiChiPhiResponse>builder()
                .data(response)
                .message("Lấy chi tiết loại chi phí thành công")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        loaiChiPhiService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Xóa loại chi phí thành công")
                .build();
    }
}
