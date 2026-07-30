package com.shahid.shopsphere.dto.payment;

import com.shahid.shopsphere.entity.PaymentMethod;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @NotNull(message = "Order Id is required")
    @Schema(description="Unique Order Id",example="2")
    private Long orderId;

    @NotNull(message = "Payment Method is required")
    @Schema(description="Payment Method",example="UPI")
    private PaymentMethod paymentMethod;
}