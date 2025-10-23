package com.vanchuyen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanchuyen.entity.TuyenDuong;

@Repository
public interface TuyenDuongRepository extends JpaRepository<TuyenDuong, Integer> {

}
