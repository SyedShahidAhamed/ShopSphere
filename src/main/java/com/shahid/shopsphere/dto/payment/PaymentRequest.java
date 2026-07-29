package com.shahid.shopsphere.dto.payment;

import com.shahid.shopsphere.entity.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @NotNull(message = "Order Id is required")
    private Long orderId;

    @NotNull(message = "Payment Method is required")
    private PaymentMethod paymentMethod;
}