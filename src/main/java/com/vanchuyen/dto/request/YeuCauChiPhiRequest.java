package com.vanchuyen.dto.request;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class YeuCauChiPhiRequest {
    @NotNull(message = "LICH_TRINH_ID_REQUIRED")
    Integer lichTrinhId;

    @NotNull(message = "LOAI_CHI_PHI_ID_REQUIRED")
    Integer loaiChiPhiId;

    @NotNull(message = "SO_TIEN_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = false, message = "SO_TIEN_INVALID")
    BigDecimal soTien;

    @NotNull(message = "NGAY_CHI_PHI_REQUIRED")
    Date ngayChiPhi;

    @Size(max = 255, message = "DIA_DIEM_CHI_PHI_TOO_LON")
    String diaDiemChiPhi;

    String moTa;

}
