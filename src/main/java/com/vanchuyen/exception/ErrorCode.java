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
