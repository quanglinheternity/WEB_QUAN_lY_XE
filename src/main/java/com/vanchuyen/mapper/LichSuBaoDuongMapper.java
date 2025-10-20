package com.vanchuyen.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vanchuyen.dto.response.LichSuBaoDuongResponse;
import com.vanchuyen.entity.LichSuBaoDuong;
import com.vanchuyen.entity.NguoiDung;

@Mapper(componentModel = "spring")
public interface LichSuBaoDuongMapper {

    @Mapping(target = "nguoiTao", source = "nguoiTao")
    @Mapping(target = "xeId", source = "xe.id")
    LichSuBaoDuongResponse toResponse(LichSuBaoDuong ls);

    Set<LichSuBaoDuongResponse> toResponseSet(Set<LichSuBaoDuong> lsSet);
    default String map(NguoiDung nguoiDung) {
        return nguoiDung != null ? nguoiDung.getHoTen() : null;
    }
}
