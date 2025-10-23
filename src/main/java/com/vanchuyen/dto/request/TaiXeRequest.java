package com.vanchuyen.dto.request;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.sql.Date;

import com.vanchuyen.enums.TrangThaiTaiXe;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Documented
@Constraint(validatedBy = TrangThaiLamViecValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidTrangThaiLamViec {
    String message() default "Nhóm chi phí không hợp lệ. Các giá trị hợp lệ: NHIEN_LIEU, PHI_DUONG_BO, SINH_HOAT, BAO_DUONG, PHAT_SINH";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class TrangThaiLamViecValidator implements ConstraintValidator<ValidTrangThaiLamViec, Integer> {


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return false; // không được null
        for (TrangThaiTaiXe tt : TrangThaiTaiXe.values()) {
            if (tt.getCode().equals(value)) {
                return true; // hợp lệ
            }
        }

        return false;
    }
}
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaiXeRequest {

    @NotBlank(message = "TAI_XE_SO_GPLX_REQUIRED")
    private String soGplx;

    @NotBlank(message = "TAI_XE_LOAI_GPLX_REQUIRED")
    private String loaiGplx;

    @NotNull(message = "TAI_XE_NGAY_CAP_GPLX_REQUIRED")
    private Date ngayCapGplx;

    @NotNull(message = "TAI_XE_NGAY_HET_HAN_GPLX_REQUIRED")
    private Date ngayHetHanGplx;

    @NotNull(message = "TAI_XE_KINH_NGHIEM_REQUIRED")
    @Min(value = 0, message = "TAI_XE_KINH_NGHIEM_INVALID")
    private Integer kinhNghiemNam;

    @NotNull(message = "TAI_XE_TRANG_THAI_REQUIRED")
    // @ValidTrangThaiLamViec
    private TrangThaiTaiXe trangThaiLamViec;

    @NotNull(message = "TAI_XE_MUC_LUONG_REQUIRED")
    @DecimalMin(value = "0.0", inclusive = false, message = "TAI_XE_MUC_LUONG_INVALID")
    private BigDecimal mucLuongCoBan;

    // @NotNull(message = "TAI_XE_NGUOI_DUNG_ID_REQUIRED")
    // private Integer nguoiDungId;
}
