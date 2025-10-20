package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "yeu_cau_chi_phi")
@Data
public class YeuCauChiPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ma_yeu_cau", unique = true)
    private String maYeuCau;
    
    @ManyToOne
    @JoinColumn(name = "lich_trinh_id")
    private LichTrinh lichTrinh;
    
    @ManyToOne
    @JoinColumn(name = "loai_chi_phi_id")
    private LoaiChiPhi loaiChiPhi;
    
    @ManyToOne
    @JoinColumn(name = "nguoi_gui_id")
    private NguoiDung nguoiGui;
    
    @Column(name = "so_tien")
    private BigDecimal soTien;
    
    @Column(name = "ngay_chi_phi")
    private Date ngayChiPhi;
    
    @Column(name = "dia_diem_chi_phi")
    private String diaDiemChiPhi;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @Column(name = "trang_thai")
    private Integer trangThai;
    
    @ManyToOne
    @JoinColumn(name = "nguoi_duyet_ql_id")
    private NguoiDung nguoiDuyetQL;
    
    @Column(name = "thoi_gian_duyet_ql")
    private LocalDateTime thoiGianDuyetQL;
    
    @Column(name = "ghi_chu_duyet_ql")
    private String ghiChuDuyetQL;
    
    @ManyToOne
    @JoinColumn(name = "nguoi_duyet_kt_id")
    private NguoiDung nguoiDuyetKT;
    
    @Column(name = "thoi_gian_duyet_kt")
    private LocalDateTime thoiGianDuyetKT;
    
    @Column(name = "ghi_chu_duyet_kt")
    private String ghiChuDuyetKT;
    
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