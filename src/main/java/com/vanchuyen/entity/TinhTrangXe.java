package com.vanchuyen.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tinh_trang_xe")
@Data
public class TinhTrangXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "xe_id")
    private Xe xe;
    
    @Column(name = "trang_thai")
    private Integer trangThai;
    
    @Column(name = "mo_ta")
    private String moTa;
    
    @Column(name = "muc_do_uu_tien")
    private Integer mucDoUuTien;
    
    @ManyToOne
    @JoinColumn(name = "nguoi_bao_cao_id")
    private NguoiDung nguoiBaoCao;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}