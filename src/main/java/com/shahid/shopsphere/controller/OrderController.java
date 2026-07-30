package com.shahid.shopsphere.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.shopsphere.dto.order.CheckoutRequest;
import com.shahid.shopsphere.dto.order.OrderResponse;
import com.shahid.shopsphere.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@EnableMethodSecurity
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@Tag(name="Orders",description="Order Management APIs")
public class OrderController {

    private final OrderService orderService;
    @Operation(
    summary = "Checkout",
    description = "Creates a new order from the current user's cart."
)
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Order created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid checkout request"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
    @PostMapping("/checkout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderResponse> checkout(
            @Valid @RequestBody CheckoutRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.checkOut(request));
    }


    @Operation(
    summary = "Get all orders",
    description = "Returns all orders placed by the current user."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<OrderResponse>> getMyOrders() {

        return ResponseEntity.ok(orderService.getMyOrders());
    }


    @Operation(
    summary = "Get order by ID",
    description = "Retrieves a specific order using its unique ID."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
    @ApiResponse(responseCode = "404", description = "Order not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")
})
    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderResponse> getOrder(
           @Parameter(description = "Unique ID of the order",example = "1")
            @PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.getOrder(orderId));
    }



    
    @Operation(
    summary = "Cancel order",
    description = "Cancels an existing order."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Order cancelled successfully"),
    @ApiResponse(responseCode = "404", description = "Order not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")
})
@PutMapping("/{orderId}/cancel")
@PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> cancelOrder(
            @PathVariable Long orderId) {

        orderService.cancelOrder(orderId);

        return ResponseEntity.ok("Order cancelled successfully.");
    }
}