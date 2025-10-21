package com.vanchuyen.dto.response;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public record TaiXeResponse(
    Integer id,
    String maTaiXe,
    String hoTen, // Lấy từ NguoiDung
    String soGplx,
    String loaiGplx,
    Date ngayCapGplx,
    Date ngayHetHanGplx,
    Integer kinhNghiemNam,
    Boolean trangThaiLamViec,
    BigDecimal mucLuongCoBan,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
