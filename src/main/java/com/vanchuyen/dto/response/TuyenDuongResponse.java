package com.vanchuyen.dto.response;

import java.math.BigDecimal;
import java.time.LocalTime;

import com.vanchuyen.enums.TrangThaiHoatDong;

public record TuyenDuongResponse(
    Integer id,
    String maTuyen,
    String tenTuyen,
    String diemDau,
    String diemCuoi,
    BigDecimal quangDuongKm,
    LocalTime thoiGianDuKien,
    String moTa,
    BigDecimal chiPhiDuKien,
    TrangThaiHoatDong trangThai,
    String trangThaiText
) {

}
