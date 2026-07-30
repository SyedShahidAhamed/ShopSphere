package com.shahid.shopsphere.dto.category;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    @Schema(description="Unique category Id",example="2")
    private Long id;
    @Schema(description="Category description",example="A best quality mobiles")
    private String description;
     @Schema(description="Category name",example="Mobiles")
    private String name;
    @Schema(description="Category active",example="true")
    private Boolean active;
       @Schema(description="Category created timestamp",  example = "2026-07-30T16:30:45")
    private LocalDateTime createdAt;
        @Schema(description="Category updated timestamp",  example = "2026-07-30T16:30:45")
    private LocalDateTime updatedAt;

}
