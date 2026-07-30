package com.shahid.shopsphere.dto.product;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description="Request for creating and updating a product")
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(max = 150, message = "Product name cannot exceed 150 characters")
    @Schema(description="Product name",example="iphone 17 pro",requiredMode=Schema.RequiredMode.REQUIRED)

    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Schema(description="Product description",example="Flagship phone with a good quality camera.")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    @Schema(description="Product price",example="79000")
    private BigDecimal price;

    @NotNull(message = "Stock is required")
    @Positive(message = "Stock must be greater than zero")
    @Schema(description="Available Stock",example="200")
    private Integer stock;

    @NotBlank(message = "Brand is required")
    @Size(max = 100, message = "Brand cannot exceed 100 characters")
    @Schema(description="Product Brand name",example="Apple")
    private String brand;

    @NotBlank(message = "Image URL is required")
    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    @Schema(description="Product image url",example="http://apple/iphone17.com")
    private String imageUrl;

    @NotNull(message = "Category is required")
    @Schema(description="Unique Category Id",example="1")
    private Long categoryId;
}