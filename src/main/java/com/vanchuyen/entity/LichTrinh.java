package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lich_trinh")
@Data
public class LichTrinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ma_chuyen", unique = true)
    private String maChuyen;
    
    @ManyToOne
    @JoinColumn(name = "tuyen_duong_id")
    private TuyenDuong tuyenDuong;
    
    @ManyToOne
    @JoinColumn(name = "xe_id")
    private Xe xe;
    
    @ManyToOne
    @JoinColumn(name = "tai_xe_chinh_id")
    private TaiXe taiXeChinh;
    
    @Column(name = "ngay_khoi_hanh")
    private LocalDateTime ngayKhoiHanh;
    
    @Column(name = "ngay_du_kien_den")
    private LocalDateTime ngayDuKienDen;
    
    @Column(name = "ngay_thuc_te_den")
    private LocalDateTime ngayThucTeDen;
    
    @Column(name = "trang_thai")
    private Integer trangThai;
    
    @Column(name = "trang_thai_duyet")
    private Integer trangThaiDuyet;
    
    @Column(name = "hang_hoa_mo_ta")
    private String hangHoaMoTa;
    
    @Column(name = "trong_luong_hang")
    private BigDecimal trongLuongHang;
    
    @ManyToOne
    @JoinColumn(name = "nguoi_tao_id")
    private NguoiDung nguoiTao;
    
    @ManyToOne
    @JoinColumn(name = "nguoi_duyet_id")
    private NguoiDung nguoiDuyet;
    
    @Column(name = "thoi_gian_duyet")
    private LocalDateTime thoiGianDuyet;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "lichTrinh")
    private List<YeuCauChiPhi> yeuCauChiPhis;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}