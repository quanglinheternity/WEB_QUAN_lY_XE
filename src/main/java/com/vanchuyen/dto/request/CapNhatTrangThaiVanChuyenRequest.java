package com.vanchuyen.dto.request;

import com.vanchuyen.enums.TrangThaiVanChuyen;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CapNhatTrangThaiVanChuyenRequest {
    @NotNull(message = "TRANG_THAI_MOI_REQUIRED")
    TrangThaiVanChuyen trangThaiMoi;
}
