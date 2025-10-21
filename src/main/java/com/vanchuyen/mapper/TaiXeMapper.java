package com.vanchuyen.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vanchuyen.dto.request.TaiXeRequest;
import com.vanchuyen.dto.response.TaiXeResponse;
import com.vanchuyen.entity.TaiXe;

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
    TaiXe toEntity(TaiXeRequest request);

    @Mapping(target = "hoTen", source = "nguoiDung.hoTen")
    TaiXeResponse toResponse(TaiXe entity);

    void updateFromRequest(TaiXeRequest request, @MappingTarget TaiXe entity);
    List<TaiXeResponse> toResponseList(List<TaiXe> entities);
}
