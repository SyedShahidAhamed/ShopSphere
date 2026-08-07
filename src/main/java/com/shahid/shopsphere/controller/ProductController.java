package com.shahid.shopsphere.controller;



import java.math.BigDecimal;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shahid.shopsphere.dto.page.PageResponse;
import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.dto.product.ProductResponse;
import com.shahid.shopsphere.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
    name="Products",
    description="Product Management APIs"
)
public class ProductController {
     private final ProductService productService;

     @Operation(
        summary="Create  Product",
        description="Create A New Product."
     )
     @ApiResponses({
        @ApiResponse(responseCode="201",description="Product Created"),
        @ApiResponse(responseCode="404",description="Product Not Created"),
        @ApiResponse(responseCode="401",description="unauthorized")
    })

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(
        
        @Valid @RequestBody ProductRequest request
        )
    {
        ProductResponse productResponse = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
   

    @Operation(
        summary = "Get All Products",
        description = "Returns a paginated list of products with optional sorting and filtering."
     )
     @ApiResponses({
        @ApiResponse(responseCode="200",description="Products Retrived Successfully"),
        @ApiResponse(responseCode="401",description="unauthorized"),
        @ApiResponse(responseCode="500",description="Internal Server Error")
    })
    @GetMapping  
    @PreAuthorize("hasAnyRole('USER','ADMIN')")                                       
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(
     @Parameter(description="Page Number",example="0")
    @RequestParam(defaultValue = "0")int page,

    @Parameter(description="Page Size",example="10")
    @RequestParam(defaultValue = "10") int size,

    @Parameter(description="Sort Field",example="price")
    @RequestParam(defaultValue = "price") String sortBy,

    @Parameter(description="Sort Direction",example="desc")
    @RequestParam(defaultValue = "asc") String direction,

    @Parameter(description="Category Name",example="Mobiles")
    @RequestParam(required =false) String category,

    @Parameter(description="Brand Name",example="Apple")
    @RequestParam(required=false) String brand,

    @Parameter(description="Minimum Price",example="5000")
    @RequestParam(required = false) BigDecimal minPrice,

    @Parameter(description="Maximum Price",example="20,000")
    @RequestParam(required = false) BigDecimal maxPrice,

    @Parameter(description="Search Keyword",example="iphone")
    @RequestParam(required =false) String keyword)
    {
        PageResponse<ProductResponse> productResponses = productService.getAllProducts(page,size,sortBy,direction,category,brand,minPrice,maxPrice,keyword);

        return ResponseEntity.ok(productResponses);
    }
     @Operation(
        summary="Get Product By ID",
        description="Fetches a product using its unique ID."
     )

     @ApiResponses({
        @ApiResponse(responseCode="200",description="Product Found"),
        @ApiResponse(responseCode="404",description="Product Not Found"),
        @ApiResponse(responseCode="401",description="unauthorized")
    })
    @GetMapping("/{id}")
     @PreAuthorize("hasAnyRole('USER','ADMIN')")
     public ResponseEntity<ProductResponse> getProductById
     (
        @Parameter(description="Product Id",example="1")
        @PathVariable Long id
     )
     {
     ProductResponse productResponse = productService.getProductById(id);

     return ResponseEntity.status(HttpStatus.OK).body(productResponse);

     }

     @Operation(
        summary="Update Product By ID",
        description="Update an Existing Product and save in database."
     )
     @ApiResponses({
        @ApiResponse(responseCode="20",description="Product Updated successfully"),
        @ApiResponse(responseCode="404",description="Product Not Found"),
        @ApiResponse(responseCode="401",description="unauthorized")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<ProductResponse> updateProduct(
        @Parameter(description="Product Id",example="1")
        @PathVariable Long id,
        @Valid @RequestBody ProductRequest request){

    ProductResponse productResponse = productService.updateProduct(id, request);

    return ResponseEntity.ok(productResponse);
     }
        
     @Operation(
        summary="Delete Product By ID",
        description="Delete an Existing Product and remove from database."
     )
     @ApiResponses({
        @ApiResponse(responseCode="204",description="Product Deleted"),
        @ApiResponse(responseCode="404",description="Product Not Found"),
        @ApiResponse(responseCode="401",description="unauthorized")
    })
        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
       public ResponseEntity<Void> deleteProduct
       (
        @Parameter(description="Product Id",example="1")
        @PathVariable Long id
        )
       {
            productService.deleteProduct(id);
              return ResponseEntity.noContent().build();
       }
    
    
}
