package com.shahid.shopsphere.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    @NotBlank(message="Category name is required")
    @Size(max=100)
    @Schema(description="Category name",example="Mobiles")
    private String name;


    @Size(max=250)
    @Schema(description="Category description",example="A best quality mobiles")
    private String description;
}
