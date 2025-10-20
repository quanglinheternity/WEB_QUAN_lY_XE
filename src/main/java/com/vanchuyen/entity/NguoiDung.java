package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nguoi_dung")
@Data
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "tai_khoan", unique = true, nullable = false)
    private String taiKhoan;
    
    @Column(name = "mat_khau", nullable = false)
    private String matKhau;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    
    @Column(name = "ho_ten")
    private String hoTen;
    
    @Column(name = "cmnd_cccd")
    private String cmndCccd;
    
    @Column(name = "ngay_sinh")
    private Date ngaySinh;
    
    @Column(name = "dia_chi")
    private String diaChi;
    
    @Column(name = "trang_thai")
    private Boolean trangThai;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToOne(mappedBy = "nguoiDung")
    private TaiXe taiXe;
    
    @OneToMany(mappedBy = "nguoiTao")
    private List<LichSuBaoDuong> lichSuBaoDuongs;
    
    @OneToMany(mappedBy = "nguoiBaoCao")
    private List<TinhTrangXe> tinhTrangXes;
    
    @OneToMany(mappedBy = "nguoiTao")
    private List<LichTrinh> lichTrinhsTao;
    
    @OneToMany(mappedBy = "nguoiDuyet")
    private List<LichTrinh> lichTrinhsDuyet;
    
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