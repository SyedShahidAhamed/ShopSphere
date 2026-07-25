package com.shahid.shopsphere.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.shopsphere.dto.category.CategoryRequest;
import com.shahid.shopsphere.dto.category.CategoryResponse;
import com.shahid.shopsphere.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request)
    {
        CategoryResponse categoryResponse = categoryService.createCategory(request);
         return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories()
    {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id)
    {
         CategoryResponse categoryResponse = categoryService.getCategoryById(id);
         return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory( @PathVariable Long id , @Valid @RequestBody CategoryRequest request )
    {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
         categoryService.deleteCategory(id);

         return ResponseEntity.noContent().build();
    }

}
