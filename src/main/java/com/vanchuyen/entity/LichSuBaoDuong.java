package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "lich_su_bao_duong")
@Data
public class LichSuBaoDuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "xe_id")
    private Xe xe;
    
    @Column(name = "ngay_bao_duong")
    private Date ngayBaoDuong;
    
    @Column(name = "loai_bao_duong")
    private String loaiBaoDuong;
    
    @Column(name = "km_hien_tai")
    private BigDecimal kmHienTai;
    
    @Column(name = "chi_phi")
    private BigDecimal chiPhi;
    
    @Column(name = "mo_ta_cong_viec", columnDefinition = "text")
    private String moTaCongViec;
    
    @Column(name = "don_vi_thuc_hien")
    private String donViThucHien;
    
    @Column(name = "file_chung_tu")
    private String fileChungTu;
    
    @ManyToOne
    @JoinColumn(name = "nguoi_tao_id")
    private NguoiDung nguoiTao;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}