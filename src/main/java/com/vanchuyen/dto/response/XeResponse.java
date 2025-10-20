package com.vanchuyen.dto.response;

import java.time.LocalDateTime;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XeResponse {
    Integer id;
    String bienSoXe;
    String tenXe;
    String tenLoaiXe;
    String mauSac;
    Integer namSanXuat;
    String taiXeChinh;
    Boolean trangThaiHoatDong;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
