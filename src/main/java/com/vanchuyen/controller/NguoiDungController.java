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

import com.vanchuyen.dto.request.NguoiDungCreateRequest;
import com.vanchuyen.dto.response.ApiResponse;
import com.vanchuyen.dto.response.NguoiDungResponse;
import com.vanchuyen.service.NguoiDungService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/nguoi-dung")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NguoiDungController {
    NguoiDungService nguoiDungService;

    @GetMapping
    public ApiResponse<List<NguoiDungResponse>> getAll() {
        return ApiResponse.<List<NguoiDungResponse>>builder()
                .data(nguoiDungService.getAll())
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<NguoiDungResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<NguoiDungResponse>builder()
                .data(nguoiDungService.getById(id))
                .build();
    }
    @PostMapping
    public ApiResponse<NguoiDungResponse> create(@RequestBody @Valid NguoiDungCreateRequest request) {
        return ApiResponse.<NguoiDungResponse>builder()
                .data(nguoiDungService.create(request))
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<NguoiDungResponse> update(@PathVariable Integer id,@RequestBody @Valid NguoiDungCreateRequest request) {
        return ApiResponse.<NguoiDungResponse>builder()
                .data(nguoiDungService.update(id, request))
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        nguoiDungService.deleted(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/myInfo")
    ApiResponse<NguoiDungResponse> getMyInfo() {
        return ApiResponse.<NguoiDungResponse>builder().data(nguoiDungService.getMyInfo()).build();
    }
    // @PutMapping("/{id}/trang-thai")
    // public ApiResponse<String> updateByTrangThai(@PathVariable Integer id) {
    //     String message = xeService.updateByTrangThai(id);
    //     return ApiResponse.<String>builder()
    //             .code(1000)
    //             .message(message)
    //             .build();
    // }
}
