package com.vanchuyen.dto.response;

import java.time.LocalDateTime;
import java.util.Set;


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
public class XeResponsedetail {
    Integer id;
    String bienSoXe;
    String tenXe;
    String tenLoaiXe;
    String mauSac;
    Integer namSanXuat;
    String taiXeChinh;
    Boolean trangThaiHoatDong;
    Set<LichSuBaoDuongResponse> lichSuBaoDuongs;
    Set<TinhTrangXeResponse> tinhTrangXes;
    Set<LichTrinhResponse> lichTrinhs;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
