package com.shahid.shopsphere.dto.order;


import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {

    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal priceAtPurchase;

    private BigDecimal subTotal;

}
