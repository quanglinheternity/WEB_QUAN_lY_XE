package com.vanchuyen.dto.response;

import java.sql.Date;
import java.time.LocalDateTime;

public record NguoiDungResponse(
    Integer id,
    String taiKhoan,
    String email,
    String soDienThoai,
    String hoTen,
    String cmndCccd,
    Date ngaySinh,
    String diaChi,
    Boolean trangThai,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    TaiXeResponse taiXe
) {

}
