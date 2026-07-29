package com.shahid.shopsphere.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.shahid.shopsphere.entity.OrderStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private String shippingAddress;

    private LocalDateTime createdAt;

    private List<OrderItemResponse> items;

}
