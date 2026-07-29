package com.shahid.shopsphere.controller;



import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
     private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request)
    {
        ProductResponse productResponse = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
   
    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(@RequestParam(defaultValue = "0")int page,
                                                                        @RequestParam(defaultValue = "10") int size,
                                                                         @RequestParam(defaultValue = "id") String sortBy,
                                                                         @RequestParam(defaultValue = "asc") String direction,
                                                                         @RequestParam(required =false) String category,
                                                                         @RequestParam(required=false) String brand,
                                                                         @RequestParam(required = false) BigDecimal minPrice,
                                                                         @RequestParam(required = false) BigDecimal maxPrice,
                                                                         @RequestParam(required =false) String keyword)
    {
        PageResponse<ProductResponse> productResponses = productService.getAllProducts(page,size,sortBy,direction,category,brand,minPrice,maxPrice,keyword);

        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/{id}")
     public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id)
     {
     ProductResponse productResponse = productService.getProductById(id);

     return ResponseEntity.status(HttpStatus.OK).body(productResponse);

     }

    @PutMapping("/{id}")
     public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequest request){

    ProductResponse productResponse = productService.updateProduct(id, request);

    return ResponseEntity.ok(productResponse);
     }
        
        @DeleteMapping("/{id}")
       public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
       {
            productService.deleteProduct(id);
              return ResponseEntity.noContent().build();
       }
    
    
}
