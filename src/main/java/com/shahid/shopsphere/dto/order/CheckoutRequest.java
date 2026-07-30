package com.shahid.shopsphere.dto.order;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutRequest {

    @NotBlank(message = "Shipping address is required")
       @Schema(description="Order Shipping Address",example="JantarMantar,NewDelhi,India.")
    private String shippingAddress;

}