package com.shahid.shopsphere.service;


import java.util.List;

import com.shahid.shopsphere.dto.order.CheckoutRequest;
import com.shahid.shopsphere.dto.order.OrderResponse;

public interface OrderService {

    OrderResponse checkOut(CheckoutRequest request);

    List<OrderResponse> getMyOrders();

    OrderResponse getOrder(Long orderId);

    void cancelOrder(Long orderId);

}
