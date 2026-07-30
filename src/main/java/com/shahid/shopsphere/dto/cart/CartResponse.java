package com.shahid.shopsphere.dto.cart;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description="Unique Cart Id",example="2")
    private Long cartId;
    
    @Schema(description="List of Cart items",example="iphone,tv,fan")
   private List<CartItemResponse> items;
      @Schema(description="Total Cart Amount",example="234444")
      private BigDecimal totalAmount;
}
