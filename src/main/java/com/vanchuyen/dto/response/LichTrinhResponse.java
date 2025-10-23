package com.vanchuyen.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vanchuyen.enums.TrangThaiDuyetLichTrinh;
import com.vanchuyen.enums.TrangThaiVanChuyen;

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
    TrangThaiVanChuyen trangThaiVanChuyen,
    String trangThaiVanChuyenText,
    TrangThaiDuyetLichTrinh trangThaiDuyet,
    String trangThaiDuyetText,
    String hangHoaMoTa,
    BigDecimal trongLuongHang,
    String nguoiTao,          // tên người tạo
    String nguoiDuyet,        // tên người duyệt
    LocalDateTime thoiGianDuyet,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
