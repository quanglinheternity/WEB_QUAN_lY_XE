package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "loai_xe")
@Data
public class LoaiXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ma_loai_xe", unique = true)
    private String maLoaiXe;
    
    @Column(name = "ten_loai_xe")
    private String tenLoaiXe;
    
    @Column(name = "tai_trong_toi_da")
    private BigDecimal taiTrongToiDa;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @Column(name = "trang_thai")
    private Boolean trangThai;
    
    @OneToMany(mappedBy = "loaiXe")
    private List<Xe> xes;
}