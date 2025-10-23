package com.vanchuyen.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class LichTrinhCreateRequest {
    private Integer tuyenDuongId;
    private Integer xeId;
    private LocalDateTime ngayKhoiHanh;
    private LocalDateTime ngayDuKienDen;
    private String hangHoaMoTa;
    private BigDecimal trongLuongHang;
}
