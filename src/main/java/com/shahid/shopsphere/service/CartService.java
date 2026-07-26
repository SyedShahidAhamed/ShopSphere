package com.shahid.shopsphere.service;

import com.shahid.shopsphere.dto.cart.AddToCartRequest;
import com.shahid.shopsphere.dto.cart.CartResponse;
import com.shahid.shopsphere.dto.cart.UpdateCartItemRequest;

public interface CartService {
    
    CartResponse addToCart(AddToCartRequest request);
    CartResponse getCart();
    CartResponse updateCartItem(Long itemId, UpdateCartItemRequest request);
    CartResponse removeCartItem(Long itemId);
    void clearCart();
}
