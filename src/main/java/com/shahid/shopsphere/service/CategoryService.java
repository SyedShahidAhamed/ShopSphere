package com.shahid.shopsphere.service;

import java.util.List;

import com.shahid.shopsphere.dto.category.CategoryRequest;
import com.shahid.shopsphere.dto.category.CategoryResponse;

public interface CategoryService {
    
    CategoryResponse createCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse updateCategory(Long id,CategoryRequest request);
    void deleteCategory(Long id);

}
