package com.vanchuyen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.vanchuyen.dto.request.TuyenDuongCreateRequest;
import com.vanchuyen.dto.response.TuyenDuongResponse;
import com.vanchuyen.entity.TuyenDuong;
import com.vanchuyen.enums.TrangThaiHoatDong;

@Mapper(componentModel = "spring")
public interface TuyenDuongMapper {
    @Mapping(target = "trangThai", source = "trangThai", qualifiedByName = "enumToCode")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lichTrinhs", ignore = true)
    @Mapping(target = "maTuyen", ignore = true)
    TuyenDuong toEntity(TuyenDuongCreateRequest request);

    @Mapping(target = "trangThai", source = "trangThai", qualifiedByName = "codeToEnum")
    @Mapping(target = "trangThaiText", source = "trangThai", qualifiedByName = "codeToDescription")
    TuyenDuongResponse toResponse(TuyenDuong entity);
    @Named("enumToCode")
    public static Boolean mapEnumToCode(TrangThaiHoatDong value) {
        return value != null ? value.getCode() : null;
    }

    @Named("codeToEnum")
    public static TrangThaiHoatDong mapCodeToEnum(Boolean code) {
        return TrangThaiHoatDong.fromCode(code);
    }
    @Named("codeToDescription")
    static String mapCodeToDescription(Boolean code) {
        if (code == null) return null;
        return TrangThaiHoatDong.fromCode(code).getDescription();
    }
}
