package com.shahid.shopsphere.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.shahid.shopsphere.dto.payment.PaymentRequest;
import com.shahid.shopsphere.dto.payment.PaymentResponse;
import com.shahid.shopsphere.entity.Order;
import com.shahid.shopsphere.entity.Payment;
import com.shahid.shopsphere.entity.PaymentStatus;
@Component
public class PaymentMapper {
    public PaymentResponse toPaymentResponse(Payment payment) {

    return PaymentResponse.builder()
            .paymentId(payment.getId())
            .orderId(payment.getOrder().getId())
            .amount(payment.getAmount())
            .paymentMethod(payment.getPaymentMethod())
            .paymentStatus(payment.getPaymentStatus())
            .transactionId(payment.getTransactionId())
            .createdAt(payment.getCreatedAt())
            .build();
}
 public Payment paymentBuilder(Order order,PaymentRequest request)
 {
     return Payment.builder()
                              .order(order)
                              .amount(order.getTotalAmount())
                              .paymentMethod(request.getPaymentMethod())
                              .paymentStatus(PaymentStatus.SUCCESS)
                              .transactionId(UUID.randomUUID().toString())
                              .build();
 }
}
