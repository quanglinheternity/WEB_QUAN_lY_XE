package com.vanchuyen.dto.request;

import java.math.BigDecimal;
import java.time.LocalTime;

import com.vanchuyen.enums.TrangThaiHoatDong;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TuyenDuongCreateRequest {

    @NotBlank(message = "TEN_TUYEN_REQUIRED")
    private String tenTuyen;

    @NotBlank(message = "DIEM_DAU_REQUIRED")
    private String diemDau;

    @NotBlank(message = "DIEM_CUOI_REQUIRED")
    private String diemCuoi;

    @NotNull(message = "QUANG_DUONG_REQUIRED")
    @DecimalMin(value = "0.1", message = "QUANG_DUONG_INVALID")
    private BigDecimal quangDuongKm;

    @NotNull(message = "THOI_GIAN_DU_KIEN")
    private LocalTime thoiGianDuKien;

    private String moTa;

    @NotNull(message = "CHI_PHI_DU_KIEN")
    @DecimalMin(value = "0", inclusive = true, message = "CHI_PHI_INVALID")
    private BigDecimal chiPhiDuKien;

    @NotNull(message = "TRANG_THAI_INVALID")
    private TrangThaiHoatDong trangThai;
}
