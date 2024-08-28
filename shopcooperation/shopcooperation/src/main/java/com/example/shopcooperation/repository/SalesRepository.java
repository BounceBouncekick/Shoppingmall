package com.example.shopcooperation.repository;


import com.example.shopcooperation.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
}