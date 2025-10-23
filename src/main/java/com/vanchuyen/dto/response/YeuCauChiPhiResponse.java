package com.vanchuyen.dto.response;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import com.vanchuyen.enums.TrangThaiYeuCauChiPhi;

public record YeuCauChiPhiResponse(
    Integer id,
    String maYeuCau,

    String maChuyen, // lấy thêm từ LichTrinh nếu cần

    String tenLoaiChiPhi,

    String tenNguoiGui,

    BigDecimal soTien,
    Date ngayChiPhi,
    String diaDiemChiPhi,
    String moTa,
    TrangThaiYeuCauChiPhi trangThai,
    String trangThaiText,

    String tenNguoiDuyetQL,
    LocalDateTime thoiGianDuyetQL,
    String ghiChuDuyetQL,

    String tenNguoiDuyetKT,
    LocalDateTime thoiGianDuyetKT,
    String ghiChuDuyetKT,

    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
