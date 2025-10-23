package com.vanchuyen.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.vanchuyen.dto.request.TaiXeRequest;
import com.vanchuyen.dto.response.TaiXeResponse;
import com.vanchuyen.entity.TaiXe;
import com.vanchuyen.enums.TrangThaiTaiXe;

@Mapper(componentModel = "spring", uses = {NguoiDungMapper.class})
public interface TaiXeMapper {
    @Mapping(target = "nguoiDung", ignore = true)
    @Mapping(target = "xes", ignore = true)
    @Mapping(target = "lichTrinhs", ignore = true)
    @Mapping(target = "baoCaoLuongs", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "maTaiXe", ignore = true)
    @Mapping(target = "trangThaiLamViec", source = "trangThaiLamViec", qualifiedByName = "enumToCode")
    TaiXe toEntity(TaiXeRequest request);

    
    @Mapping(target = "hoTen", source = "nguoiDung.hoTen")
    // map enum từ int code
    @Mapping(target = "trangThaiLamViec", expression = "java(TrangThaiTaiXe.fromCode(entity.getTrangThaiLamViec()))")
    // map text mô tả
    @Mapping(target = "trangThaiLamViecText", expression = "java(TrangThaiTaiXe.fromCode(entity.getTrangThaiLamViec()).getDescription())")
    TaiXeResponse toResponse(TaiXe entity);

    @Mapping(target = "nguoiDung", ignore = true)
    @Mapping(target = "xes", ignore = true)
    @Mapping(target = "lichTrinhs", ignore = true)
    @Mapping(target = "baoCaoLuongs", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "maTaiXe", ignore = true)
    @Mapping(target = "trangThaiLamViec", source = "trangThaiLamViec", qualifiedByName = "enumToCode")
    void updateFromRequest(TaiXeRequest request, @MappingTarget TaiXe entity);
    List<TaiXeResponse> toResponseList(List<TaiXe> entities);

    @Named("enumToCode")
    public static int mapEnumToCode(TrangThaiTaiXe value) {
        return value != null ? value.getCode() : 0;
    }

    @Named("codeToEnum")
    public static TrangThaiTaiXe mapCodeToEnum(int code) {
        return TrangThaiTaiXe.fromCode(code);
    }
}
