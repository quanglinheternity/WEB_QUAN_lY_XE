package com.vanchuyen.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vanchuyen.dto.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException e) {
        log.error("error", e);
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_ERROR;
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    // @ExceptionHandler(AuthorizationDeniedException.class)
    // public ResponseEntity<ApiResponse<?>> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
    //     ApiResponse<?> response = ApiResponse.builder()
    //             .code(ErrorCode.UNAUTHORIZED.getCode())
    //             .message(ErrorCode.UNAUTHORIZED.getMessage())
    //             .build();
    //     return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    // }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Void>> handleAppException(AppException appException) {
        ErrorCode errorCode = appException.getErrorCode();
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(response);
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException exception) {
    //     List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

    //     // Gom tất cả lỗi thành danh sách errorCode
    //     List<ErrorCode> errors = fieldErrors.stream()
    //             .map(fieldError -> {
    //                 String enumKey = fieldError.getDefaultMessage();
    //                 try {
    //                     return ErrorCode.valueOf(enumKey);
    //                 } catch (Exception e) {
    //                     return ErrorCode.INVALID_REQUEST;
    //                 }
    //             })
    //             .toList();

    //     // Trả về danh sách lỗi
    //     Map<String, Object> errorResponse = new HashMap<>();
    //     errorResponse.put(
    //             "errors",
    //             errors.stream()
    //                     .map(err -> Map.of(
    //                             "code", err.getCode(),
    //                             "message", err.getMessage()))
    //                     .toList());

    //     ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
    //             .code(ErrorCode.INVALID_REQUEST.getCode())
    //             .message(ErrorCode.INVALID_REQUEST.getMessage())
    //             .data(errorResponse)
    //             .build();

    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    // }
     // Xử lý lỗi do @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String code = error.getDefaultMessage();

            // Tìm ErrorCode tương ứng theo name
            ErrorCode errorCode = Arrays.stream(ErrorCode.values())
                    .filter(e -> e.name().equals(code))
                    .findFirst()
                    .orElse(ErrorCode.INTERNAL_SERVER_ERROR);

            errors.put(field, errorCode.getMessage());
        });

        response.put("code", 400);
        response.put("message", "Dữ liệu không hợp lệ");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }
}
