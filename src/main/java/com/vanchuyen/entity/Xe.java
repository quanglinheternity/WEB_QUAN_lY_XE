package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "xe")
@Data
public class Xe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "bien_so_xe", unique = true, nullable = false)
    private String bienSoXe;
    
    @ManyToOne
    @JoinColumn(name = "loai_xe_id")
    private LoaiXe loaiXe;
    
    @Column(name = "ten_xe")
    private String tenXe;
    
    @Column(name = "nam_san_xuat")
    private Integer namSanXuat;
    
    @Column(name = "mau_sac")
    private String mauSac;
    
    @Column(name = "so_khung")
    private String soKhung;
    
    @Column(name = "so_may")
    private String soMay;
    
    @Column(name = "ngay_dang_kiem")
    private Date ngayDangKiem;
    
    @Column(name = "ngay_bao_hiem")
    private Date ngayBaoHiem;
    
    @ManyToOne
    @JoinColumn(name = "tai_xe_chinh_id")
    private TaiXe taiXeChinh;
    
    @Column(name = "trang_thai_hoat_dong")
    private Boolean trangThaiHoatDong;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "xe")
    private List<LichSuBaoDuong> lichSuBaoDuongs;
    
    @OneToMany(mappedBy = "xe")
    private List<TinhTrangXe> tinhTrangXes;
    
    @OneToMany(mappedBy = "xe")
    private List<LichTrinh> lichTrinhs;
    
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