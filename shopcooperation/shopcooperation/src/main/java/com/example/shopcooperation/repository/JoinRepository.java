package com.example.shopcooperation.repository;

import com.example.shopcooperation.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<UserEntity,String> {

    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}