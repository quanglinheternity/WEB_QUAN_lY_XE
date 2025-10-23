package com.vanchuyen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.vanchuyen.dto.request.YeuCauChiPhiRequest;
import com.vanchuyen.dto.response.YeuCauChiPhiResponse;
import com.vanchuyen.entity.YeuCauChiPhi;


@Mapper(componentModel = "spring", uses = {LichTrinhMapper.class})
public interface YeuCauChiPhiMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lichTrinh.id", ignore = true)
    @Mapping(target = "loaiChiPhi.id", ignore = true)
    @Mapping(target = "nguoiDuyetQL", ignore = true)
    @Mapping(target = "nguoiDuyetKT", ignore = true)
    @Mapping(target = "thoiGianDuyetQL", ignore = true)
    @Mapping(target = "thoiGianDuyetKT", ignore = true)
    @Mapping(target = "ghiChuDuyetQL", ignore = true)
    @Mapping(target = "ghiChuDuyetKT", ignore = true)
    @Mapping(target = "nguoiGui", ignore = true)
    @Mapping(target = "maYeuCau", ignore = true)
    @Mapping(target = "trangThai", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    YeuCauChiPhi toEntity(YeuCauChiPhiRequest request);

    @Mapping(target = "maChuyen", source = "lichTrinh.maChuyen")
    @Mapping(target = "tenLoaiChiPhi", source = "loaiChiPhi.tenLoaiChiPhi")
    @Mapping(target = "tenNguoiGui", source = "nguoiGui.hoTen")
    @Mapping(target = "tenNguoiDuyetQL", source = "nguoiDuyetQL.hoTen")
    @Mapping(target = "tenNguoiDuyetKT", source = "nguoiDuyetKT.hoTen")
     @Mapping(target = "trangThai", expression = "java(TrangThaiYeuCauChiPhi.fromCode(entity.getTrangThai()))")
    // map text mô tả
    @Mapping(target = "trangThaiText", expression = "java(TrangThaiYeuCauChiPhi.fromCode(entity.getTrangThai()).getDescription())")
    YeuCauChiPhiResponse toResponse(YeuCauChiPhi entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lichTrinh", ignore = true)
    @Mapping(target = "loaiChiPhi", ignore = true)
    @Mapping(target = "nguoiDuyetQL", ignore = true)
    @Mapping(target = "nguoiDuyetKT", ignore = true)
    @Mapping(target = "thoiGianDuyetQL", ignore = true)
    @Mapping(target = "thoiGianDuyetKT", ignore = true)
    @Mapping(target = "ghiChuDuyetQL", ignore = true)
    @Mapping(target = "ghiChuDuyetKT", ignore = true)
    @Mapping(target = "nguoiGui", ignore = true)
    @Mapping(target = "maYeuCau", ignore = true)
    @Mapping(target = "trangThai", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateFromRequest(YeuCauChiPhiRequest request,@MappingTarget YeuCauChiPhi entity);
}
