package com.vanchuyen.dto.response;

import java.math.BigDecimal;
import java.sql.Date;
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
public class LichSuBaoDuongResponse {
    Integer id;
    Integer xeId;           // Id xe liên quan
    Date ngayBaoDuong;
    String loaiBaoDuong;
    BigDecimal kmHienTai;
    BigDecimal chiPhi;
    String moTaCongViec;
    String donViThucHien;
    String fileChungTu;
    String nguoiTao;        // Tên người tạo
    LocalDateTime createdAt;
}
