package com.vanchuyen.repository;

import com.vanchuyen.entity.LichTrinh;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LichTrinhRepository extends JpaRepository<LichTrinh, Integer> {
    Optional<LichTrinh> findByMaChuyen(String maChuyen);
    List<LichTrinh> findByTrangThai(Integer trangThai);
    List<LichTrinh> findByTrangThaiDuyet(Integer trangThaiDuyet);
    
    @Query("SELECT lt FROM LichTrinh lt WHERE lt.ngayKhoiHanh BETWEEN :startDate AND :endDate")
    List<LichTrinh> findByNgayKhoiHanhBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<LichTrinh> findByTaiXeChinhId(Integer taiXeChinhId);
    List<LichTrinh> findByXeId(Integer xeId);
      // Tìm kiếm theo maChuyen chứa từ khóa
    Page<LichTrinh> findByMaChuyenContainingIgnoreCase(String maChuyen, Pageable pageable);

    // Có thể mở rộng: tìm kiếm theo trạng thái
    Page<LichTrinh> findByTrangThai(Integer trangThai, Pageable pageable);

    // Kết hợp maChuyen + trạng thái
    Page<LichTrinh> findByMaChuyenContainingIgnoreCaseAndTrangThai(String maChuyen, Integer trangThai, Pageable pageable);
    @Query(value = """
        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
        FROM lich_trinh l
        WHERE l.xe_id = :xeId
          AND l.trang_thai_duyet IN (0, 1)
          AND (
              (:ngayKhoiHanh BETWEEN l.ngay_khoi_hanh AND l.ngay_du_kien_den)
              OR (:ngayDuKienDen BETWEEN l.ngay_khoi_hanh AND l.ngay_du_kien_den)
              OR (l.ngay_khoi_hanh BETWEEN :ngayKhoiHanh AND :ngayDuKienDen)
          )
    """, nativeQuery = true)
    long countLichTrinhTrung(
            @Param("xeId") Integer xeId,
            @Param("ngayKhoiHanh") LocalDateTime ngayKhoiHanh,
            @Param("ngayDuKienDen") LocalDateTime ngayDuKienDen
    );
}