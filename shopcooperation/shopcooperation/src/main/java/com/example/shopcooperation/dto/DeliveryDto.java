package com.example.shopcooperation.dto;

import com.example.shopcooperation.entity.Delivery;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryDto {

    private String city;
    private String street;
    private String zipcode;

    @Builder
    public DeliveryDto(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public static DeliveryDto toEntity(Delivery delivery){
        return DeliveryDto.builder()
                .city(delivery.getCity())
                .street(delivery.getStreet())
                .zipcode(delivery.getZipcode())
                .build();
    }
}
