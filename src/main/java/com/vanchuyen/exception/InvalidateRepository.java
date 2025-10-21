package com.vanchuyen.exception;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanchuyen.entity.InvalidatedToken;


@Repository
public interface InvalidateRepository extends JpaRepository<InvalidatedToken, String> {}
