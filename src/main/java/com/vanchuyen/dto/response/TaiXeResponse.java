package com.vanchuyen.dto.response;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import com.vanchuyen.enums.TrangThaiTaiXe;

public record TaiXeResponse(
    Integer id,
    String maTaiXe,
    String hoTen, // Lấy từ NguoiDung
    String soGplx,
    String loaiGplx,
    Date ngayCapGplx,
    Date ngayHetHanGplx,
    Integer kinhNghiemNam,
    TrangThaiTaiXe trangThaiLamViec,
    String trangThaiLamViecText,
    BigDecimal mucLuongCoBan,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
