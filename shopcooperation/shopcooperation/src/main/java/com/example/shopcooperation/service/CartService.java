package com.example.shopcooperation.service;

import com.example.shopcooperation.dto.CartItemDto;
import com.example.shopcooperation.entity.CartItem;
import com.example.shopcooperation.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartItemDto addItemToCart(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem(
                cartItemDto.getProductName(),
                cartItemDto.getQuantity(),
                cartItemDto.getPrice()
        );

        CartItem savedItem = cartRepository.save(cartItem);
        return toDto(savedItem);
    }

    public List<CartItemDto> getCartItems() {
        List<CartItem> cartItems = cartRepository.findAll();
        return cartItems.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void removeItemFromCart(Long id) {
        cartRepository.deleteById(id);
    }

    private CartItemDto toDto(CartItem cartItem) {
        return CartItemDto.builder()
                .id(cartItem.getId())
                .productName(cartItem.getProductName())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }
}