package com.example.shopcooperation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItemDto {
    private Long id;
    private String productName;
    private int quantity;
    private double price;
}
