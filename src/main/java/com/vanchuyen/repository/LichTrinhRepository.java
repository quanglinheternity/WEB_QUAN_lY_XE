package com.vanchuyen.repository;

import com.vanchuyen.entity.LichTrinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
}