package com.shahid.shopsphere.mapper;

import org.springframework.stereotype.Component;

import com.shahid.shopsphere.dto.category.CategoryResponse;
import com.shahid.shopsphere.entity.Category;
@Component
public class CategoryMapper {
    
     public CategoryResponse toCategoryResponse(Category category)
    {
        return   CategoryResponse.builder()
                               .id(category.getId())
                               .name(category.getName())
                               .description(category.getDescription())
                               .active(category.getActive())
                               .createdAt(category.getCreatedAt())
                               .updatedAt(category.getUpdatedAt())
                               .build();

    }
}
