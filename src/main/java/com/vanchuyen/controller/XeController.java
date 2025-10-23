package com.vanchuyen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanchuyen.dto.request.XeCreateRequest;
import com.vanchuyen.dto.request.XeUpdateRequest;
import com.vanchuyen.dto.response.ApiResponse;
import com.vanchuyen.dto.response.XeResponse;
import com.vanchuyen.dto.response.XeResponsedetail;
import com.vanchuyen.service.XeService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/xes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class XeController {
    XeService xeService;

    @GetMapping
    public ApiResponse<List<XeResponse>> getAll() {
        return ApiResponse.<List<XeResponse>>builder()
                .message("Lấy danh sách thành công")
                .data(xeService.getAll())
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<XeResponsedetail> getById(@PathVariable Integer id) {
        return ApiResponse.<XeResponsedetail>builder()
                .message("Lấy chi tiết thành công")
                .data(xeService.getById(id))
                .build();
    }
    @PostMapping
    public ApiResponse<XeResponse> create(@RequestBody @Valid XeCreateRequest request) {
        return ApiResponse.<XeResponse>builder()
                .data(xeService.create(request))
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<XeResponse> update(@PathVariable Integer id,@RequestBody @Valid XeUpdateRequest request) {
        return ApiResponse.<XeResponse>builder()
                .data(xeService.update(id, request))
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        xeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/trang-thai")
    public ApiResponse<String> updateByTrangThai(@PathVariable Integer id) {
        String message = xeService.updateByTrangThai(id);
        return ApiResponse.<String>builder()
                .code(1000)
                .message(message)
                .build();
    }
}
