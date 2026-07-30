package com.shahid.shopsphere.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description="Unique Product Id",example="2")
    private Long productId;
    
    @NotNull(message="quantity is required")
    @Size(min=1,message="quantity should be atleast 1")
        @Schema(description="Product Quantity",example="2")
    private Integer quantity;
}
