package com.vanchuyen.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LichTrinhResponse(
    Integer id,
    String maChuyen,
    Integer xeId,
    String bienSoXe,          // lấy từ xe
    String taiXeChinh,        // tên tài xế chính
    String tuyenDuong,        // tên tuyến đường
    LocalDateTime ngayKhoiHanh,
    LocalDateTime ngayDuKienDen,
    LocalDateTime ngayThucTeDen,
    Integer trangThai,
    Integer trangThaiDuyet,
    String hangHoaMoTa,
    BigDecimal trongLuongHang,
    String nguoiTao,          // tên người tạo
    String nguoiDuyet,        // tên người duyệt
    LocalDateTime thoiGianDuyet,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
