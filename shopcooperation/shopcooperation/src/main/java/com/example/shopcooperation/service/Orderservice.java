package com.example.shopcooperation.service;


import com.example.shopcooperation.dto.DeliveryDto;
import com.example.shopcooperation.dto.OrderItemDto;
import com.example.shopcooperation.entity.*;
import com.example.shopcooperation.repository.DeliveryRepository;
import com.example.shopcooperation.repository.OrderRepository;
import com.example.shopcooperation.repository.ProductRepository;
import com.example.shopcooperation.repository.SalesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class Orderservice {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final SalesRepository salesRepository;
    private final RedisTemplate<String, Object> redisTemplate;


    @Transactional
    public void order(OrderItemDto orderItemDto, String name, String uuid, DeliveryDto deliveryDto) {
        Product product = (Product) redisTemplate.opsForValue().get(uuid);
        if (product == null) {
            // Redis에 캐시가 없는 경우, 데이터베이스에서 조회
            product = productRepository.findByUuid(uuid);
            if (product != null) {
                // Redis에 제품 정보를 캐싱
                redisTemplate.opsForValue().set(uuid, product);
            }
        }

        if (product != null) {
            log.info("Product found: {}", product);

            // 상품을 사용하여 주문 상품 생성
            OrderItem orderItem = OrderItem.createOrderItem(product, orderItemDto);

            // 주문 생성 (주문 상품을 배열로 넘겨줌)
            Order order = Order.createOrder(product, orderItem);
            orderRepository.save(order);

            // 배송 정보 생성 및 저장
            Delivery delivery = Delivery.createDelivery(deliveryDto);
            deliveryRepository.save(delivery);

            // 재고 차감
            product.removeStock(orderItemDto.getCount());

            Sales sales = salesRepository.findById(1L).orElse(new Sales());
            sales.addToTotalSales(order.getTotalPrice());
            salesRepository.save(sales);

        } else {
            log.warn("Product not found for UUID: {}", uuid);
            // 상품을 찾을 수 없는 경우에 대한 처리
            // 예를 들어, 예외를 던지거나 로그를 남기는 등의 작업 수행
        }
    }



    @Transactional
    public void cancelOrder(String uuid) {
        // 주문 엔티티 조회
        Order order = orderRepository.findByUuid(uuid);
        log.info("cancelOrder : {}", order);

        if (order != null) {
            double originalPrice = order.getTotalPrice(); // 원래 주문 가격을 가져옴
            log.info("취소_originalPrice :{}",originalPrice);
            // 주문 취소
            order.cancel();
            orderRepository.save(order); // 취소 상태를 저장

            // 취소된 주문 금액을 총 매출액에서 빼기
            Sales sales = salesRepository.findById(1L).orElse(new Sales());
            sales.subtractFromTotalSales(originalPrice);
            salesRepository.save(sales);
        }
    }
}