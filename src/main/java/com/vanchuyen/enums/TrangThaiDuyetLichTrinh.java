package com.vanchuyen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrangThaiDuyetLichTrinh {
    CHO_DUYET(0, "Chờ duyệt"),
    DA_DUYET(1, "Đã duyệt"),
    TU_CHOI(2, "Từ chối");

    private final int code;
    private final String description;
    public static TrangThaiDuyetLichTrinh fromCode(int code) {
        for (TrangThaiDuyetLichTrinh value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid TrangThaiDuyetLichTrinh code: " + code);
    }
}
