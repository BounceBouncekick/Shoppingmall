package com.example.shopcooperation.entity;

import com.example.shopcooperation.dto.DeliveryDto;
import com.example.shopcooperation.em.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "jpa_delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

    @Builder
    public Delivery(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.status = DeliveryStatus.READY; // 기본값 설정
    }

    public DeliveryDto toDto() {
        return DeliveryDto.builder()
                .city(this.city)
                .street(this.street)
                .zipcode(this.zipcode)
                .build();
    }

    public static Delivery createDelivery(DeliveryDto deliveryDto) {
        return Delivery.builder()
                .city(deliveryDto.getCity())
                .street(deliveryDto.getStreet())
                .zipcode(deliveryDto.getZipcode())
                .build();
    }
}
