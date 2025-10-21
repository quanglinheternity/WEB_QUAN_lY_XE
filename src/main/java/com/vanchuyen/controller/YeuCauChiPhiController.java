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

import com.vanchuyen.dto.request.DuyetYeuCauRequest;
import com.vanchuyen.dto.request.YeuCauChiPhiRequest;
import com.vanchuyen.dto.response.ApiResponse;
import com.vanchuyen.dto.response.YeuCauChiPhiResponse;
import com.vanchuyen.service.YeuCauChiPhiService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/yeu-cau-chi-phi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class YeuCauChiPhiController {
    YeuCauChiPhiService yeuCauChiPhiService;
    @PostMapping
    public ApiResponse<YeuCauChiPhiResponse> create(@Valid @RequestBody YeuCauChiPhiRequest request) {
        return ApiResponse.<YeuCauChiPhiResponse>builder()
                .data(yeuCauChiPhiService.create(request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<YeuCauChiPhiResponse>> getAll() {
        return ApiResponse.<List<YeuCauChiPhiResponse>>builder()
                .data(yeuCauChiPhiService.getAll())
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<YeuCauChiPhiResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<YeuCauChiPhiResponse>builder()
                .data(yeuCauChiPhiService.getById(id))
                .build();
    }
    @GetMapping("/getByNguoiGui")
    public ApiResponse<List<YeuCauChiPhiResponse>> getByNguoiGui() {
        return ApiResponse.<List<YeuCauChiPhiResponse>>builder()
                .data(yeuCauChiPhiService.getByNguoiGui())
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<YeuCauChiPhiResponse> update(@PathVariable Integer id, @Valid @RequestBody YeuCauChiPhiRequest request) {
        return ApiResponse.<YeuCauChiPhiResponse>builder()
                .data(yeuCauChiPhiService.update(id, request))
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        yeuCauChiPhiService.delete(id);
        return ApiResponse.<Void>builder().build();
    }
    @PutMapping("/{id}/trang-thai")
    public ApiResponse<YeuCauChiPhiResponse> updateByTrangThai(@PathVariable Integer id, @RequestBody DuyetYeuCauRequest request) {
        YeuCauChiPhiResponse response = yeuCauChiPhiService.duyetYeuCau(id, request);
       String message = switch (response.trangThai()) {
            case 1 -> "Yêu cầu chi phí đã được duyệt thành công";
            case 2 -> "Yêu cầu chi phí đã được thanh toán thành công";
            case 3 -> "Yêu cầu chi phí đã bị từ chối";
            default -> "Cập nhật trạng thái thành công";
        };
        return ApiResponse.<YeuCauChiPhiResponse>builder()
                .code(1000)
                .message(message)
                .data(response)
                .build();
    }
}
