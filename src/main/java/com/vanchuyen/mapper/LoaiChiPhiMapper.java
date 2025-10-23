package com.vanchuyen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vanchuyen.dto.request.LoaiChiPhiRequest;
import com.vanchuyen.dto.response.LoaiChiPhiResponse;
import com.vanchuyen.entity.LoaiChiPhi;

@Mapper(componentModel = "spring")
public interface LoaiChiPhiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "yeuCauChiPhis", ignore = true)
    @Mapping(target = "maLoaiChiPhi", ignore = true)
    LoaiChiPhi toEntity(LoaiChiPhiRequest request);

    LoaiChiPhiResponse toResponse(LoaiChiPhi entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "yeuCauChiPhis", ignore = true)
    @Mapping(target = "maLoaiChiPhi", ignore = true)
    void updateEntity(@MappingTarget LoaiChiPhi entity, LoaiChiPhiRequest request);
}
