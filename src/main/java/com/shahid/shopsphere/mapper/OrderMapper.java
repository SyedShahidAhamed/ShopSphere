package com.shahid.shopsphere.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shahid.shopsphere.dto.order.OrderItemResponse;
import com.shahid.shopsphere.dto.order.OrderResponse;
import com.shahid.shopsphere.entity.Order;
import com.shahid.shopsphere.entity.OrderItem;
@Component
public class OrderMapper {
    private OrderItemResponse toOrderItemResponse(OrderItem item) {
    return OrderItemResponse.builder()
            .productId(item.getProduct().getId())
            .productName(item.getProduct().getName())
            .quantity(item.getQuantity())
            .priceAtPurchase(item.getPriceAtPurchase())
            .subTotal(
                item.getPriceAtPurchase()
                    .multiply(BigDecimal.valueOf(item.getQuantity()))
            )
            .build();
}
public OrderResponse toOrderResponse(Order order) {

    List<OrderItemResponse> items = order.getOrderItems()
            .stream()
            .map(this::toOrderItemResponse)
            .toList();

    return OrderResponse.builder()
            .orderId(order.getId())
            .status(order.getStatus())
            .totalAmount(order.getTotalAmount())
            .shippingAddress(order.getShippingAddress())
            .createdAt(order.getCreatedAt())
            .items(items)
            .build();
}

}
