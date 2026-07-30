package com.shahid.shopsphere.dto.cart;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
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
     @Schema(description="Unique Product Id",example="2")
    private Long productId;

 @NotBlank(message="productname is required")
 @Schema(description="Product name",example="Iphone 17")
private String productName;
 
@Schema(description="unitPrice of a product",example="32000")
 private BigDecimal unitPrice;

 @NotNull(message="product quantity is required")
 @Size(min=1,message="quantity should be atleast 1")
 @Schema(description="Product Quantity",example="2")
 private Integer quantity;
 
 @Schema(description="subtotal of a cart",example="40000")
 private BigDecimal subtotal;

}
