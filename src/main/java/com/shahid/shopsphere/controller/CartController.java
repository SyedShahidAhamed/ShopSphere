package com.shahid.shopsphere.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.shopsphere.dto.cart.AddToCartRequest;
import com.shahid.shopsphere.dto.cart.CartResponse;
import com.shahid.shopsphere.dto.cart.UpdateCartItemRequest;
import com.shahid.shopsphere.service.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartService cartService;

    // Add Product to Cart
    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(
            @Valid @RequestBody AddToCartRequest request) {

        return new ResponseEntity<>(
                cartService.addToCart(request),
                HttpStatus.CREATED);
    }

    // Get Current User Cart
    @GetMapping
    public ResponseEntity<CartResponse> getCart() {

        return ResponseEntity.ok(cartService.getCart());
    }

    // Update Cart Item Quantity
    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItem(
            @PathVariable Long itemId,
            @Valid @RequestBody UpdateCartItemRequest request) {

        return ResponseEntity.ok(
                cartService.updateCartItem(itemId, request));
    }

    // Remove Single Item from Cart
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> removeCartItem(
            @PathVariable Long itemId) {

        return ResponseEntity.ok(
                cartService.removeCartItem(itemId));
    }

    // Clear Entire Cart
    @DeleteMapping
    public ResponseEntity<Void> clearCart() {

        cartService.clearCart();

        return ResponseEntity.noContent().build();
    }
}