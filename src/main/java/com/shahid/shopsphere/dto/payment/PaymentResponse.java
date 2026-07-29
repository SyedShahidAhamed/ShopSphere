package com.shahid.shopsphere.dto.payment;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.shahid.shopsphere.entity.PaymentMethod;
import com.shahid.shopsphere.entity.PaymentStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long paymentId;

    private Long orderId;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private String transactionId;

    private LocalDateTime createdAt;
}
