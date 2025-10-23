package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.vanchuyen.enums.NhomLoaiChiPhi;

@Entity
@Table(name = "loai_chi_phi")
@Data
public class LoaiChiPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ma_loai_chi_phi", unique = true)
    private String maLoaiChiPhi;
    
    @Column(name = "ten_loai_chi_phi")
    private String tenLoaiChiPhi;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "nhom_chi_phi")
    private NhomLoaiChiPhi nhomChiPhi;
    
    @Column(name = "trang_thai")
    private Boolean trangThai;
    
    @OneToMany(mappedBy = "loaiChiPhi")
    private List<YeuCauChiPhi> yeuCauChiPhis;
}