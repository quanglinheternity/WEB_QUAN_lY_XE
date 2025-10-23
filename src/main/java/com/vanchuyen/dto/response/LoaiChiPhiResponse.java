package com.vanchuyen.dto.response;

public record LoaiChiPhiResponse(
    Integer id,
    String maLoaiChiPhi,
    String tenLoaiChiPhi,
    String moTa,
    String nhomChiPhi,
    Boolean trangThai
) {

}
