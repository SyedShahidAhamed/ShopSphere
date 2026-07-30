package com.shahid.shopsphere.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.shopsphere.dto.cart.AddToCartRequest;
import com.shahid.shopsphere.dto.cart.CartResponse;
import com.shahid.shopsphere.dto.cart.UpdateCartItemRequest;
import com.shahid.shopsphere.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/cart")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
@Tag(name="Cart",description="Carts Management APIs")
public class CartController {

    private final CartService cartService;
     @Operation(
        summary="Add Product to Cart",
        description="Creates a new Cart By adding a Products"
     )
   
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Product added to cart successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "404", description = "Product not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})


    // Add Product to Cart
    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponse> addToCart(
            @Valid @RequestBody AddToCartRequest request) {

        return new ResponseEntity<>(
                cartService.addToCart(request),
                HttpStatus.CREATED);//201
    }
  
    @Operation(
    summary = "Get current cart",
    description = "Retrieves the current user's shopping cart."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "404", description = "Cart not found")
})
     
    // Get Current User Cart
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponse> getCart() {

        return ResponseEntity.ok(cartService.getCart());
    }


    @Operation(
    summary = "Update cart item",
    description = "Updates the quantity of a product in the shopping cart."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cart updated successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "404", description = "Cart item not found")
})
    // Update Cart Item Quantity
    @PutMapping("/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponse> updateCartItem(
        @Parameter(
    description = "Unique ID of the cart item",
    example = "1"
)
            @PathVariable Long itemId,
            @Valid @RequestBody UpdateCartItemRequest request) {

        return ResponseEntity.ok(
                cartService.updateCartItem(itemId, request));
    }
    
    @Operation(
    summary = "Remove cart item",
    description = "Removes a specific product from the shopping cart."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Cart item removed successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "404", description = "Cart item not found")
})

    // Remove Single Item from Cart
    @DeleteMapping("/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponse> removeCartItem(
        @Parameter(
    description = "Unique ID of the cart item",
    example = "1"
)
            @PathVariable Long itemId) {

        return ResponseEntity.ok(
                cartService.removeCartItem(itemId));
    }

    @Operation(
    summary = "Clear cart",
    description = "Removes all products from the current user's shopping cart."
)
@ApiResponses({
    @ApiResponse(responseCode = "204", description = "Cart cleared successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")
})
    // Clear Entire Cart
    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> clearCart() {

        cartService.clearCart();

        return ResponseEntity.noContent().build();
    }
}