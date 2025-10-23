package com.vanchuyen.enums;

import com.vanchuyen.exception.AppException;
import com.vanchuyen.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NhomLoaiChiPhi {
    NHIEN_LIEU("NHIEN_LIEU"),
    PHI_DUONG_BO("PHI_DUONG_BO"),
    SINH_HOAT("SINH_HOAT"),
    BAO_DUONG("BAO_DUONG"),
    PHAT_SINH("PHAT_SINH");

    private final String code;

    public static NhomLoaiChiPhi fromCode(String code) {
        for (NhomLoaiChiPhi value : values()) {
            if (value.getCode().equalsIgnoreCase(code)) {
                return value;
            }
        }
        // Nếu không match, bắn ra AppException
        throw new AppException(
            ErrorCode.INVALID_DATA
        );
    }
}
