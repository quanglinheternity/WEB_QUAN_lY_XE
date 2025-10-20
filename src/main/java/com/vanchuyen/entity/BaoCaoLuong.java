package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "bao_cao_luong")
@Data
public class BaoCaoLuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "thang_nam")
    private Date thangNam;
    
    @ManyToOne
    @JoinColumn(name = "tai_xe_id")
    private TaiXe taiXe;
    
    @Column(name = "so_chuyen_hoan_thanh")
    private Integer soChuyenHoanThanh;
    
    @Column(name = "tong_quang_duong")
    private BigDecimal tongQuangDuong;
    
    @Column(name = "luong_co_ban")
    private BigDecimal luongCoBan;
    
    @Column(name = "thuong_chuyen")
    private BigDecimal thuongChuyen;
    
    @Column(name = "phu_cap")
    private BigDecimal phuCap;
    
    @Column(name = "khau_tru")
    private BigDecimal khauTru;
    
    @Column(name = "tong_luong")
    private BigDecimal tongLuong;
    
    @Column(name = "trang_thai_thanh_toan")
    private Boolean trangThaiThanhToan;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
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