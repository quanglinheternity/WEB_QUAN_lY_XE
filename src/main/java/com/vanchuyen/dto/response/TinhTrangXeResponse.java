package com.vanchuyen.dto.response;

import java.time.LocalDateTime;

public record TinhTrangXeResponse(
    Integer id,
    Integer xeId,          // Id xe liên quan
    Integer trangThai,
    String moTa,
    Integer mucDoUuTien,
    String nguoiBaoCao,    // tên người báo cáo
    LocalDateTime createdAt
) {}
