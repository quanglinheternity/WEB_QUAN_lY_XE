package com.vanchuyen.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanchuyen.entity.NguoiDung;
import com.vanchuyen.entity.YeuCauChiPhi;

@Repository
public interface YeuCauChiPhiRepository extends JpaRepository<YeuCauChiPhi, Integer> {
    Optional<YeuCauChiPhi> findByMaYeuCau(String maYeuCau);
    boolean existsByMaYeuCau(String maYeuCau);
    List<YeuCauChiPhi> findByNguoiGui(NguoiDung nguoiDung);
}