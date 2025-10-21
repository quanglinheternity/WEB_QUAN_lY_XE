package com.vanchuyen.repository;

import com.vanchuyen.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTaiKhoan(String taiKhoan);
    Optional<NguoiDung> findByEmail(String email);
    boolean existsByTaiKhoan(String taiKhoan);
    boolean existsByEmail(String email);
    
    boolean existsByTaiKhoanAndIdNot(String taiKhoan, Integer id);
}