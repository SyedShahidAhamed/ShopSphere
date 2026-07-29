package com.shahid.shopsphere.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shahid.shopsphere.dto.cart.CartItemResponse;
import com.shahid.shopsphere.dto.cart.CartResponse;
import com.shahid.shopsphere.entity.Cart;
import com.shahid.shopsphere.entity.CartItem;
@Component
public class CartMapper {

    public CartResponse toCartResponse(Cart cart) {

        List<CartItemResponse> items = cart.getCartItems()
                .stream()
                .map(this::toCartItemResponse)
                .toList();

        return new CartResponse(
                cart.getId(),
                items,
                cart.getTotalAmount()
        );
    }

    public CartItemResponse toCartItemResponse(CartItem item) {

        return new CartItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getUnitPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()))
        );
    }
}