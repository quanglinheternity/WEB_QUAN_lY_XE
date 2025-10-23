package com.vanchuyen.enums;

import lombok.Getter;

@Getter
public enum TrangThaiVanChuyen {
    CHUA_KHOI_HANH(0, "Chưa khởi hành"),
    DANG_VAN_CHUYEN(1, "Đang vận chuyển"),
    TAM_DUNG(2, "Tạm dừng"),
    DA_DEN_NOI(3, "Đã đến nơi"),
    HUY_CHUYEN(4, "Hủy chuyến");

    private final int code;
    private final String description;

    TrangThaiVanChuyen(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TrangThaiVanChuyen fromCode(int code) {
        for (TrangThaiVanChuyen tt : values()) {
            if (tt.code == code) {
                return tt;
            }
        }
        throw new IllegalArgumentException("Invalid TrangThaiVanChuyen code: " + code);
    }
}