package com.shahid.shopsphere.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCartResponse {
    
    @NotNull(message = "productId is required")
    private Long productId;
    
    @NotNull(message="quantity is required")
    @Size(min=1,message="quantity should be atleast 1")
    private Integer quantity;
}
