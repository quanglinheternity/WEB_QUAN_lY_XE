package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tuyen_duong")
@Data
public class TuyenDuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ma_tuyen", unique = true)
    private String maTuyen;
    
    @Column(name = "ten_tuyen")
    private String tenTuyen;
    
    @Column(name = "diem_dau")
    private String diemDau;
    
    @Column(name = "diem_cuoi")
    private String diemCuoi;
    
    @Column(name = "quang_duong_km")
    private BigDecimal quangDuongKm;
    
    @Column(name = "thoi_gian_du_kien")
    private LocalTime thoiGianDuKien;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @Column(name = "chi_phi_du_kien")
    private BigDecimal chiPhiDuKien;
    
    @Column(name = "trang_thai")
    private Boolean trangThai;
    
    @OneToMany(mappedBy = "tuyenDuong")
    private List<LichTrinh> lichTrinhs;
}