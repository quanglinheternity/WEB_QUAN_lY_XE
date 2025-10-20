package com.vanchuyen.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XeUpdateRequest {

    // // --- ID xe cần cập nhật ---
    // @NotNull(message = "XE_ID_NULL")
    // Integer id;

    // --- Biển số xe ---
    @NotBlank(message = "XE_BIEN_SO_EMPTY")
    @Size(max = 15, message = "XE_BIEN_SO_MAX_LENGTH")
    @Pattern(regexp = "^[0-9A-Z-]+$", message = "XE_BIEN_SO_INVALID_FORMAT")
    String bienSoXe;

    // --- Loại xe ---
    @NotNull(message = "XE_LOAI_XE_NULL")
    Integer loaiXeId;

    // --- Thông tin cơ bản ---
    @Size(max = 50, message = "XE_TEN_MAX_LENGTH")
    String tenXe;

    @Min(value = 1990, message = "XE_NAM_SAN_XUAT_MIN")
    @Max(value = 2025, message = "XE_NAM_SAN_XUAT_MAX")
    Integer namSanXuat;

    @Size(max = 30, message = "XE_MAU_SAC_MAX_LENGTH")
    String mauSac;

    @Size(max = 50, message = "XE_SO_KHUNG_MAX_LENGTH")
    String soKhung;

    @Size(max = 50, message = "XE_SO_MAY_MAX_LENGTH")
    String soMay;

    // --- Ngày bảo hiểm & đăng kiểm ---
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "XE_NGAY_DANG_KIEM_FORMAT")
    String ngayDangKiem;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "XE_NGAY_BAO_HIEM_FORMAT")
    String ngayBaoHiem;

    // --- Tài xế & trạng thái ---
    Integer taiXeChinhId;

    @NotNull(message = "XE_TRANG_THAI_NULL")
    Boolean trangThaiHoatDong;
}
