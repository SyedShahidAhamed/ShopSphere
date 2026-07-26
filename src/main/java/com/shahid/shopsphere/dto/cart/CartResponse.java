package com.shahid.shopsphere.dto.cart;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    @NotNull(message="cartid is required")
    private Long cartId;
   
   private List<CartItemResponse> items;

      private BigDecimal totalAmount;
}
