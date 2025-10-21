package com.vanchuyen.dto.request;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
public class TaiXeRequest {

    @NotBlank(message = "TAI_XE_SO_GPLX_REQUIRED")
    private String soGplx;

    @NotBlank(message = "TAI_XE_LOAI_GPLX_REQUIRED")
    private String loaiGplx;

    @NotNull(message = "TAI_XE_NGAY_CAP_GPLX_REQUIRED")
    private Date ngayCapGplx;

    @NotNull(message = "TAI_XE_NGAY_HET_HAN_GPLX_REQUIRED")
    private Date ngayHetHanGplx;

    @NotNull(message = "TAI_XE_KINH_NGHIEM_REQUIRED")
    @Min(value = 0, message = "TAI_XE_KINH_NGHIEM_INVALID")
    private Integer kinhNghiemNam;

    @NotNull(message = "TAI_XE_TRANG_THAI_REQUIRED")
    private Boolean trangThaiLamViec;

    @NotNull(message = "TAI_XE_MUC_LUONG_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = false, message = "TAI_XE_MUC_LUONG_INVALID")
    private BigDecimal mucLuongCoBan;

    // @NotNull(message = "TAI_XE_NGUOI_DUNG_ID_REQUIRED")
    // private Integer nguoiDungId;
}
