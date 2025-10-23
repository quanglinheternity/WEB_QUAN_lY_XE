package com.vanchuyen.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanchuyen.dto.response.ApiResponse;
import com.vanchuyen.dto.response.TuyenDuongResponse;
import com.vanchuyen.service.TuyenDuongService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tuyen-duong")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TuyenDuongController {
    TuyenDuongService tuyenDuongService;
    @GetMapping
    public ApiResponse<List<TuyenDuongResponse>> getAll() {
        return ApiResponse.<List<TuyenDuongResponse>>builder()
                .message("Lấy danh sách tuyến đường thành công.")
                .data(tuyenDuongService.getAllTuyenDuong())
                .build();
    }
}
