package com.example.shopcooperation.repository;

import com.example.shopcooperation.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByUuid(String Uuid);

}
