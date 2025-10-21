package com.vanchuyen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "Lỗi không xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ID_KEY(1001, "ID không hợp lệ", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(1002, "Lỗi hệ thống, vui lòng thử lại sau", HttpStatus.INTERNAL_SERVER_ERROR),


    // Authentication
    AUTHENTICATION_REQUIRED(2002, "Bạn chưa đăng nhập", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(2003, "Token đã hết hạn", HttpStatus.UNAUTHORIZED),

    INVALID_STATE_TRANSITION(2004, "Trạng thái đã được xử lý", HttpStatus.BAD_REQUEST),
    THONG_TIN_TAI_XE_REQUIRED(2005, "Thống tin tài xế không được để trống", HttpStatus.BAD_REQUEST),
    // 400 - Bad Request
    INVALID_REQUEST(1000, "Request không hợp lệ", HttpStatus.BAD_REQUEST),
    
    //Loại xe
    LOAIXE_MA_REQUIRED(2001, "Mã loại xe không được để trống", HttpStatus.BAD_REQUEST),
    LOAIXE_MA_MAXLENGTH(2002, "Mã loại xe không được vượt quá 20 ký tự", HttpStatus.BAD_REQUEST),
    LOAIXE_TEN_REQUIRED(2003, "Tên loại xe không được để trống", HttpStatus.BAD_REQUEST),
    LOAIXE_TEN_MAXLENGTH(2004, "Tên loại xe không được vượt quá 100 ký tự", HttpStatus.BAD_REQUEST),
    LOAIXE_TAITRONG_REQUIRED(2005, "Tải trọng tối đa không được để trống", HttpStatus.BAD_REQUEST),
    LOAIXE_TAITRONG_MIN(2006, "Tải trọng tối đa phải lớn hơn 0", HttpStatus.BAD_REQUEST),
    LOAIXE_MOTA_MAXLENGTH(2007, "Mô tả không được vượt quá 255 ký tự", HttpStatus.BAD_REQUEST),
    LOAIXE_TRANGTHAI_REQUIRED(2008, "Trạng thái không được để trống (true/false)", HttpStatus.BAD_REQUEST),
    // --- Xe ---
    XE_BIEN_SO_EMPTY(2001, "Biển số xe không được để trống", HttpStatus.BAD_REQUEST),
    XE_BIEN_SO_MAX_LENGTH(2002, "Biển số xe không vượt quá 15 ký tự", HttpStatus.BAD_REQUEST),
    XE_BIEN_SO_INVALID_FORMAT(2003, "Biển số xe chỉ được chứa chữ in hoa, số và dấu '-'", HttpStatus.BAD_REQUEST),

    XE_LOAI_XE_NULL(2004, "Loại xe không được để trống", HttpStatus.BAD_REQUEST),
    XE_TEN_MAX_LENGTH(2005, "Tên xe không vượt quá 50 ký tự", HttpStatus.BAD_REQUEST),

    XE_NAM_SAN_XUAT_MIN(2006, "Năm sản xuất không được nhỏ hơn 1990", HttpStatus.BAD_REQUEST),
    XE_NAM_SAN_XUAT_MAX(2007, "Năm sản xuất không được lớn hơn 2025", HttpStatus.BAD_REQUEST),

    XE_MAU_SAC_MAX_LENGTH(2008, "Màu sắc không vượt quá 30 ký tự", HttpStatus.BAD_REQUEST),
    XE_SO_KHUNG_MAX_LENGTH(2009, "Số khung không vượt quá 50 ký tự", HttpStatus.BAD_REQUEST),
    XE_SO_MAY_MAX_LENGTH(2010, "Số máy không vượt quá 50 ký tự", HttpStatus.BAD_REQUEST),

    XE_NGAY_DANG_KIEM_FORMAT(2011, "Ngày đăng kiểm phải theo định dạng yyyy-MM-dd", HttpStatus.BAD_REQUEST),
    XE_NGAY_BAO_HIEM_FORMAT(2012, "Ngày bảo hiểm phải theo định dạng yyyy-MM-dd", HttpStatus.BAD_REQUEST),

    XE_TRANG_THAI_NULL(2013, "Trạng thái hoạt động không được để trống", HttpStatus.BAD_REQUEST),
    XE_EXISTED(2014, "Biển số xe đã tồn tại", HttpStatus.BAD_REQUEST),

    LOAI_XE_NOT_FOUND(2015, "Loại xe không tìm thấy", HttpStatus.BAD_REQUEST),
    XE_NOT_FOUND(2016, "Xe không tìm thấy", HttpStatus.BAD_REQUEST),
    TAI_XE_NOT_FOUND(2017, "Tài xế không tìm thấy", HttpStatus.BAD_REQUEST),

    //người
    
    NGUOI_DUNG_EXISTED(1000, "Tài khoản người dùng tồn tại", HttpStatus.BAD_REQUEST),
    NGUOI_DUNG_NOT_FOUND(1001, "Người dùng không tìm thấy", HttpStatus.BAD_REQUEST),
    NGUOI_DUNG_ALREADY_EXISTS(1002, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    
    TAI_KHOAN_NOT_BLANK(1001, "Tài khoản không được để trống", HttpStatus.BAD_REQUEST),
    TAI_KHOAN_SIZE(1002, "Tài khoản không quá 50 ký tự", HttpStatus.BAD_REQUEST),

    MAT_KHAU_NOT_BLANK(1003, "Mật khẩu không được để trống", HttpStatus.BAD_REQUEST),
    MAT_KHAU_SIZE(1004, "Mật khẩu từ 6 đến 100 ký tự", HttpStatus.BAD_REQUEST),

    EMAIL_INVALID(1005, "Email không hợp lệ", HttpStatus.BAD_REQUEST),

    SO_DIEN_THOAI_PATTERN(1006, "Số điện thoại phải từ 9 đến 12 chữ số", HttpStatus.BAD_REQUEST),

    HO_TEN_NOT_BLANK(1007, "Họ tên không được để trống", HttpStatus.BAD_REQUEST),

    CMND_CCCD_PATTERN(1008, "CMND/CCCD phải từ 9 đến 12 chữ số", HttpStatus.BAD_REQUEST),

    NGAY_SINH_PAST(1009, "Ngày sinh phải là ngày trong quá khứ", HttpStatus.BAD_REQUEST),

    AUTHENTICATION_FAILED(2001, "Sai tài khoản hoặc mật khẩu", HttpStatus.UNAUTHORIZED),
     LICH_TRINH_ID_REQUIRED(1001, "Lịch trình không được để trống", HttpStatus.BAD_REQUEST),
    LOAI_CHI_PHI_ID_REQUIRED(1002, "Loại chi phí không được để trống", HttpStatus.BAD_REQUEST),
    SO_TIEN_REQUIRED(1003, "Số tiền không được để trống", HttpStatus.BAD_REQUEST),
    SO_TIEN_INVALID(1004, "Số tiền phải lớn hơn 0", HttpStatus.BAD_REQUEST),
    NGAY_CHI_PHI_REQUIRED(1005, "Ngày chi phí không được để trống", HttpStatus.BAD_REQUEST),
    DIA_DIEM_CHI_PHI_TOO_LONG(1006, "Địa điểm chi phí không được vượt quá 255 ký tự", HttpStatus.BAD_REQUEST),
    YEU_CAU_CHI_PHI_NOT_FOUND(1007, "Yêu cầu chi phí không tìm thấy", HttpStatus.BAD_REQUEST),
    LICH_TRINH_NOT_FOUND(1008, "Lịch trình không tìm thấy", HttpStatus.BAD_REQUEST),
    LOAI_CHI_PHI_NOT_FOUND(1009, "Loại chi phí không tìm thấy", HttpStatus.BAD_REQUEST),
    TAI_XE_SO_GPLX_REQUIRED(3101, "Số GPLX không được để trống", HttpStatus.BAD_REQUEST),
    TAI_XE_LOAI_GPLX_REQUIRED(3102, "Loại GPLX không được để trống", HttpStatus.BAD_REQUEST),
    TAI_XE_NGAY_CAP_GPLX_REQUIRED(3103, "Ngày cấp GPLX không được để trống", HttpStatus.BAD_REQUEST),
    TAI_XE_NGAY_HET_HAN_GPLX_REQUIRED(3104, "Ngày hết hạn GPLX không được để trống", HttpStatus.BAD_REQUEST),
    TAI_XE_KINH_NGHIEM_REQUIRED(3105, "Kinh nghiệm (năm) không được để trống", HttpStatus.BAD_REQUEST),
    TAI_XE_KINH_NGHIEM_INVALID(3106, "Kinh nghiệm năm không được âm", HttpStatus.BAD_REQUEST),
    TAI_XE_TRANG_THAI_REQUIRED(3107, "Trạng thái làm việc không được để trống", HttpStatus.BAD_REQUEST),
    TAI_XE_MUC_LUONG_REQUIRED(3108, "Mức lương cơ bản không được để trống", HttpStatus.BAD_REQUEST),
    TAI_XE_MUC_LUONG_INVALID(3109, "Mức lương cơ bản phải lớn hơn 0", HttpStatus.BAD_REQUEST),
    TAI_XE_NGUOI_DUNG_ID_REQUIRED(3110, "ID người dùng không được để trống", HttpStatus.BAD_REQUEST),

    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
