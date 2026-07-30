package com.shahid.shopsphere.dto.order;


import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {
    @Schema(description="Unique Product Id",example="1")
    private Long productId;
 @Schema(description="Product name",example="Iphone 16")
    private String productName;
    @Schema(description="Product Quantity",example="2")
    private Integer quantity;
  @Schema(description="unitPrice of a product",example="32000")
    private BigDecimal priceAtPurchase;
@Schema(description="subtotal of a orderitems",example="40000")
    private BigDecimal subTotal;

}
