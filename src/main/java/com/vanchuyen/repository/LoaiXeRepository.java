package com.vanchuyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanchuyen.entity.LoaiXe;

@Repository
public interface LoaiXeRepository extends JpaRepository<LoaiXe, Integer> {

}
