package com.shahid.shopsphere.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String brand;

    private String imageUrl;

    private Boolean active;

    private Long categoryId;

    private String categoryName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}