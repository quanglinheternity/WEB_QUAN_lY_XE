package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tai_xe")
@Data
public class TaiXe {
    @Id
    private Integer id;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private NguoiDung nguoiDung;
    
    @Column(name = "ma_tai_xe", unique = true)
    private String maTaiXe;
    
    @Column(name = "so_gplx")
    private String soGplx;
    
    @Column(name = "loai_gplx")
    private String loaiGplx;
    
    @Column(name = "ngay_cap_gplx")
    private Date ngayCapGplx;
    
    @Column(name = "ngay_het_han_gplx")
    private Date ngayHetHanGplx;
    
    @Column(name = "kinh_nghiem_nam")
    private Integer kinhNghiemNam;
    
    @Column(name = "trang_thai_lam_viec", columnDefinition = "int default 0")
    private int trangThaiLamViec;
    
    @Column(name = "muc_luong_co_ban")
    private BigDecimal mucLuongCoBan;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    
    
    @OneToMany(mappedBy = "taiXeChinh")
    private List<Xe> xes;
    
    @OneToMany(mappedBy = "taiXeChinh")
    private List<LichTrinh> lichTrinhs;
    
    @OneToMany(mappedBy = "taiXe")
    private List<BaoCaoLuong> baoCaoLuongs;
    
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