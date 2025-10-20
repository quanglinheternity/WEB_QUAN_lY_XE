package com.vanchuyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanchuyen.entity.TaiXe;

@Repository
public interface TaiXeRepository extends JpaRepository<TaiXe, Integer> {

}
