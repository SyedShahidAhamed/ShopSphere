package com.shahid.shopsphere.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.shahid.shopsphere.entity.OrderStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    @Schema(description="Unique Order Id",example="2")
    private Long orderId;
    @Schema(description="Order Status",example="PENDING")
    private OrderStatus status;
    @Schema(description="Order totalAmount",example="20000")
    private BigDecimal totalAmount;
    @Schema(description="Order Shipping Address",example="JantarMantar,NewDelhi,India.")
    private String shippingAddress;

    private LocalDateTime createdAt;
     @Schema(description="List of Order Items",example="phone,fan,tv")
    private List<OrderItemResponse> items;

}
