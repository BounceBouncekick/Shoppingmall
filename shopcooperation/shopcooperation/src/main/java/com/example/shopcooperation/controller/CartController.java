package com.example.shopcooperation.controller;

import com.example.shopcooperation.dto.CartItemDto;
import com.example.shopcooperation.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addItemToCart(@RequestBody CartItemDto cartItemDto) {
        CartItemDto addedItem = cartService.addItemToCart(cartItemDto);
        return ResponseEntity.ok(addedItem);
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getCartItems() {
        List<CartItemDto> cartItems = cartService.getCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long id) {
        cartService.removeItemFromCart(id);
        return ResponseEntity.ok().build();
    }
}
