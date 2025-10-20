package com.vanchuyen.mapper;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vanchuyen.dto.response.TinhTrangXeResponse;
import com.vanchuyen.entity.TinhTrangXe;

@Mapper(componentModel = "spring")
public interface TinhTrangXeMapper {
    @Mapping(target = "xeId", source = "xe.id")
    @Mapping(target = "nguoiBaoCao", source = "nguoiBaoCao.hoTen")
    TinhTrangXeResponse toResponse(TinhTrangXe tt);

    Set<TinhTrangXeResponse> toResponseSet(Set<TinhTrangXe> ttSet);
}
