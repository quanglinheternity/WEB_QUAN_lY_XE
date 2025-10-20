package com.vanchuyen.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.vanchuyen.dto.request.XeCreateRequest;
import com.vanchuyen.dto.request.XeUpdateRequest;
import com.vanchuyen.dto.response.XeResponse;
import com.vanchuyen.dto.response.XeResponsedetail;
import com.vanchuyen.entity.Xe;

@Mapper(componentModel = "spring",  uses = {LichSuBaoDuongMapper.class, TinhTrangXeMapper.class, LichTrinhMapper.class})
public interface XeMapper {
    @Mapping(target = "tenLoaiXe", source = "loaiXe.tenLoaiXe")
    @Mapping(target = "taiXeChinh", source = "taiXeChinh.nguoiDung.hoTen")
    XeResponse toXeResponse(Xe xe);

    @Mapping(target = "tenLoaiXe", source = "loaiXe.tenLoaiXe")
    @Mapping(target = "taiXeChinh", source = "taiXeChinh.nguoiDung.hoTen")
    @Mapping(target = "lichSuBaoDuongs", source = "lichSuBaoDuongs")
    @Mapping(target = "tinhTrangXes", source = "tinhTrangXes")
    @Mapping(target = "lichTrinhs", source = "lichTrinhs")
    XeResponsedetail toXeResponsedetail(Xe xe);



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lichSuBaoDuongs", ignore = true)
    @Mapping(target = "lichTrinhs", ignore = true)
    @Mapping(target = "tinhTrangXes", ignore = true)
    @Mapping(target = "loaiXe", ignore = true)
    @Mapping(target = "taiXeChinh", ignore = true)
    @Mapping(target = "ngayDangKiem", source = "ngayDangKiem", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "ngayBaoHiem", source = "ngayBaoHiem", dateFormat = "yyyy-MM-dd")
    Xe toXeCreateRequest(XeCreateRequest xe);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "lichSuBaoDuongs", ignore = true)
    @Mapping(target = "lichTrinhs", ignore = true)
    @Mapping(target = "tinhTrangXes", ignore = true)
    @Mapping(target = "loaiXe", ignore = true)
    @Mapping(target = "taiXeChinh", ignore = true)
    @Mapping(target = "ngayDangKiem", source = "ngayDangKiem", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "ngayBaoHiem", source = "ngayBaoHiem", dateFormat = "yyyy-MM-dd")
    void updateXeFromUpdateRequest(XeUpdateRequest xeUpdateRequest, @MappingTarget Xe xe);
}
