package com.example.shopcooperation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private OrderItemDto orderItem;
    private DeliveryDto delivery;
}
