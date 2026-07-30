package com.shahid.shopsphere.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
     @Schema(description="Unique Product Id",example="1")
    private Long id;
    @Schema(description="Product name",example="Iphone 16")
    private String name;
  @Schema(description="Product description",example=" Brandnew Flagship phone with a good quality camera.")
    private String description;
    @Schema(description="Product price",example="15000")
    private BigDecimal price;
    @Schema(description="Available Stock",example="200")
    private Integer stock;
    @Schema(description="Product Brand Name",example="Iphone")
    private String brand;
    @Schema(description="Product image url",example="http://apple/iphone17.com")
    private String imageUrl;
    @Schema(description="product Active",example="true")
    private Boolean active;
 @Schema(description="Unique Category Id",example="1")
    private Long categoryId;
   @Schema(description="Category Name",example="Mobiles")
    private String categoryName;
    @Schema(description="Product created timestamp",  example = "2026-07-30T16:30:45")

    private LocalDateTime createdAt;
    @Schema(description="Product updated timestamp",  example = "2026-07-30T16:30:45")
    private LocalDateTime updatedAt;
}