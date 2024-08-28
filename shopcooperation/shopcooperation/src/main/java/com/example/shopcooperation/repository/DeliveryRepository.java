package com.example.shopcooperation.repository;

import com.example.shopcooperation.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Delivery findByCity(String city);
}
