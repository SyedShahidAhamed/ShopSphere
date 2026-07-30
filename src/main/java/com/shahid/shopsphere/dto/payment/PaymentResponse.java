package com.shahid.shopsphere.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.shahid.shopsphere.entity.PaymentMethod;
import com.shahid.shopsphere.entity.PaymentStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response object containing payment details")
public class PaymentResponse {

    @Schema(
        description = "Unique identifier of the payment",
        example = "1"
    )
    private Long paymentId;

    @Schema(
        description = "Unique identifier of the associated order",
        example = "101"
    )
    private Long orderId;

    @Schema(
        description = "Total payment amount",
        example = "59999.99"
    )
    private BigDecimal amount;

    @Schema(
        description = "Payment method used",
        example = "UPI"
    )
    private PaymentMethod paymentMethod;

    @Schema(
        description = "Current payment status",
        example = "SUCCESS"
    )
    private PaymentStatus paymentStatus;

    @Schema(
        description = "Unique transaction identifier",
        example = "TXN202607300001"
    )
    private String transactionId;

    @Schema(
        description = "Payment creation timestamp",
        example = "2026-07-30T16:30:45"
    )
    private LocalDateTime createdAt;
}