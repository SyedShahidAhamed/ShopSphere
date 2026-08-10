package com.shahid.shopsphere.controller;

import java.util.List;

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

import com.shahid.shopsphere.dto.category.CategoryRequest;
import com.shahid.shopsphere.dto.category.CategoryResponse;
import com.shahid.shopsphere.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@EnableMethodSecurity
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/categories")
@Tag(
    name="Categories",
    description="Category Management APIs"
)
public class CategoryController {
    
    private final CategoryService categoryService;
    @Operation(
    summary = "Create a new category",
    description = "Creates a new product category."
)
@ApiResponses({
    @ApiResponse(responseCode = "201", description = "Category created successfully"),
    @ApiResponse(responseCode = "400", description = "Validation failed"),
    @ApiResponse(responseCode = "401", description = "Unauthorized"),
    @ApiResponse(responseCode = "409", description = "Category already exists")
})
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request)
    {
        CategoryResponse categoryResponse = categoryService.createCategory(request);
         return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }
   @Operation(
    summary = "Get all categories",
    description = "Retrieves all available product categories."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")
})

    @GetMapping
    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<CategoryResponse>> getAllCategories()
    {
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
  
    @Operation(
    summary = "Get category by ID",
    description = "Retrieves a category using its unique ID."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
    @ApiResponse(responseCode = "404", description = "Category not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")
})
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<CategoryResponse> getCategoryById(
        @Parameter(
    description = "Unique ID of the category",example = "1")
    @PathVariable Long id)
    {
         CategoryResponse categoryResponse = categoryService.getCategoryById(id);
         return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }
    
    @Operation(
    summary = "Update category",
    description = "Updates an existing category."
)
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Category updated successfully"),
    @ApiResponse(responseCode = "404", description = "Category not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")
})
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategory( @Parameter(
    description = "Unique ID of the category",example = "1")
    @PathVariable Long id ,
     @Valid @RequestBody CategoryRequest request )
    {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(categoryResponse);
    }
    

    @Operation(
    summary = "Delete category",
    description = "Deletes a category using its unique ID."
)
@ApiResponses({
    @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
    @ApiResponse(responseCode = "404", description = "Category not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")
})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(
        @Parameter(
    description = "Unique ID of the category",example = "1")
        @PathVariable Long id)
    {
         categoryService.deleteCategory(id);

         return ResponseEntity.noContent().build();
    }

}
