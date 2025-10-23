package com.vanchuyen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrangThaiTaiXe {
    RANH(0,"Rảnh"),
    DANG_CHAY(1,"Đang chạy"),
    NGHI_PHEP(2,"Nghỉ phép"),
    TAM_KHOA(3,"Tạm khóa");

    private final Integer code;
    private final String description;
    public static TrangThaiTaiXe fromCode(Integer code) {
        for (TrangThaiTaiXe tt : values()) {
            if (tt.code == code) {
                return tt;
            }
        }
        throw new IllegalArgumentException("Invalid TrangThaiTaiXe code: " + code);
    }
}
