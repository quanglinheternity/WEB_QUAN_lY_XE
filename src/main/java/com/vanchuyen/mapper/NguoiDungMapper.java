package com.vanchuyen.mapper;

import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.dto.request.NguoiDungCreateRequest;
import com.vanchuyen.dto.response.NguoiDungResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper {

    NguoiDungMapper INSTANCE = Mappers.getMapper(NguoiDungMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "taiXe", ignore = true)
    @Mapping(target = "lichSuBaoDuongs", ignore = true)
    @Mapping(target = "lichTrinhsTao", ignore = true)
    @Mapping(target = "lichTrinhsDuyet", ignore = true)
    @Mapping(target = "tinhTrangXes", ignore = true)
    NguoiDung toCreateEntity(NguoiDungCreateRequest request);

    NguoiDungResponse toResponse(NguoiDung entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "taiXe", ignore = true)
    @Mapping(target = "lichSuBaoDuongs", ignore = true)
    @Mapping(target = "lichTrinhsTao", ignore = true)
    @Mapping(target = "lichTrinhsDuyet", ignore = true)
    @Mapping(target = "tinhTrangXes", ignore = true)
    void updateFromRequest(NguoiDungCreateRequest request, @MappingTarget NguoiDung entity);
}
