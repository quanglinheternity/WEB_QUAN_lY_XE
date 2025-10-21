package com.vanchuyen.dto.request;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NguoiDungCreateRequest {

    @NotBlank(message = "TAI_KHOAN_NOT_BLANK")
    @Size(max = 50, message = "TAI_KHOAN_SIZE")
    String taiKhoan;

    @NotBlank(message = "MAT_KHAU_NOT_BLANK")
    @Size(min = 6, max = 100, message = "MAT_KHAU_SIZE")
    String matKhau;

    @Email(message = "EMAIL_INVALID")
    String email;

    @Pattern(regexp = "\\d{9,12}", message = "SO_DIEN_THOAI_PATTERN")
    String soDienThoai;

    @NotBlank(message = "HO_TEN_NOT_BLANK")
    String hoTen;

    @Pattern(regexp = "\\d{9,12}", message = "CMND_CCCD_PATTERN")
    String cmndCccd;

    @Past(message = "NGAY_SINH_PAST")
    Date ngaySinh;

    String diaChi;

    Boolean trangThai;

    
}
