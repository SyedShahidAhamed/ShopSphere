package com.shahid.shopsphere.dto.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartItemRequest {
    
    @NotNull(message="Quantity is Reequired")
    @Min(value=1,message="Quantity must be at least 1")
    @Schema(description="Product Quantity",example="2")
    private Integer quantity;
}
