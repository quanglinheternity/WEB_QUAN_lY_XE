package com.vanchuyen.repository;

import com.vanchuyen.entity.Xe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface XeRepository extends JpaRepository<Xe, Integer> {
    boolean existsByBienSoXe(String bienSoXe);
    Optional<Xe> findByBienSoXe(String bienSoXe);
    List<Xe> findByTrangThaiHoatDong(Boolean trangThaiHoatDong);
    List<Xe> findByTaiXeChinhId(Integer taiXeChinhId);
}