package com.vanchuyen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrangThaiHoatDongXe {
    HOAT_DONG(true, "Hoạt động"),
    NGUNG_HOAT_DONG(false, "Ngừng hoạt động");
    // NGHI_PHEP(null, "Nghỉ phép");

    private final Boolean code;
    private final String description;


    public static TrangThaiHoatDongXe fromCode(Boolean code) {
        for (TrangThaiHoatDongXe tt : values()) {
            if (tt.code == code) {
                return tt;
            }
        }
        throw new IllegalArgumentException("Invalid driver status code: " + code);
    }
}
