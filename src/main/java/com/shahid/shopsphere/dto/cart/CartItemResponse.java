package com.shahid.shopsphere.dto.cart;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
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
public class CartItemResponse {
    @NotNull(message="productid is required")
    private Long productId;

 @NotBlank(message="productname is required")
private String productName;
 
 private BigDecimal unitPrice;

 @NotNull(message="product quantity is required")
 @Size(min=1,message="quantity should be atleast 1")
 private Integer quantity;
 
 private BigDecimal subtotal;

}
