package com.vanchuyen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrangThaiYeuCauChiPhi {
    CHO_DUYET_QL(0, "Chờ duyệt quản lý"),
    DA_DUYET_QL(1, "Đã duyệt quản lý"),
    DA_DUYET_KT(2, "Đã duyệt kế toán"),
    TU_CHOI(3, "Từ chối");

    private final int code;
    private final String description;

    public static TrangThaiYeuCauChiPhi fromCode(Integer code) {
        for (TrangThaiYeuCauChiPhi tt : values()) {
            if (tt.code == code) {
                return tt;
            }
        }
        throw new IllegalArgumentException("Invalid TrangThaiYeuCauChiPhi code: " + code);
    }
}
