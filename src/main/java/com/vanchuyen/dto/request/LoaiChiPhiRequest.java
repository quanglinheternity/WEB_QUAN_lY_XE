package com.vanchuyen.dto.request;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vanchuyen.enums.NhomLoaiChiPhi;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
@Documented
@Constraint(validatedBy = NhomChiPhiValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface ValidNhomChiPhi {
    String message() default "Nhóm chi phí không hợp lệ. Các giá trị hợp lệ: NHIEN_LIEU, PHI_DUONG_BO, SINH_HOAT, BAO_DUONG, PHAT_SINH";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class NhomChiPhiValidator implements ConstraintValidator<ValidNhomChiPhi, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return false;

        for (NhomLoaiChiPhi nhom : NhomLoaiChiPhi.values()) {
            if (nhom.getCode().equalsIgnoreCase(value)) {
                return true; // hợp lệ
            }
        }

        // Tùy chỉnh thông báo lỗi
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                "Nhóm chi phí không hợp lệ. Giá trị hợp lệ: NHIEN_LIEU, PHI_DUONG_BO, SINH_HOAT, BAO_DUONG, PHAT_SINH"
        ).addConstraintViolation();

        return false;
    }
}
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoaiChiPhiRequest {

    @NotBlank(message = "TEN_LOAI_CHI_PHI_NULL")
    String tenLoaiChiPhi;

    String moTa;

    @NotBlank(message = "NHOM_CHI_PHI_NULL")
    @ValidNhomChiPhi
    String nhomChiPhi;
    @NotNull(message = "TRANG_THAI_REQUIRED")
    Boolean trangThai;
    
}
