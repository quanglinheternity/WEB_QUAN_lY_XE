package com.vanchuyen.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vanchuyen.dto.response.LichTrinhResponse;
import com.vanchuyen.entity.LichTrinh;
import com.vanchuyen.entity.NguoiDung;

@Mapper(componentModel = "spring")
public interface LichTrinhMapper {
    
    @Mapping(target = "xeId", source = "xe.id")
    @Mapping(target = "bienSoXe", source = "xe.bienSoXe")
    @Mapping(target = "taiXeChinh", source = "taiXeChinh.nguoiDung.hoTen") // hoặc taiXeChinh.nguoiDung.hoTen nếu có NguoiDung
    @Mapping(target = "tuyenDuong", source = "tuyenDuong.tenTuyen") // map tên tuyến đường
    @Mapping(target = "nguoiTao", source = "nguoiTao")             // MapStruct gọi map(NguoiDung)
    @Mapping(target = "nguoiDuyet", source = "nguoiDuyet")         // MapStruct gọi map(NguoiDung)
    // @Mapping(target = "yeuCauChiPhis", source = "yeuCauChiPhis")
    LichTrinhResponse toResponse(LichTrinh lt);

    Set<LichTrinhResponse> toResponseSet(Set<LichTrinh> ltSet);

    // Map NguoiDung → String (hoTen)
    default String map(NguoiDung nguoiDung) {
        return nguoiDung != null ? nguoiDung.getHoTen() : null;
    }
}
