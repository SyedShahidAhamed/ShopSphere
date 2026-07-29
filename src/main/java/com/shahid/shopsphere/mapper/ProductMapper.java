package com.shahid.shopsphere.mapper;

import org.springframework.stereotype.Component;

import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.dto.product.ProductResponse;
import com.shahid.shopsphere.entity.Category;
import com.shahid.shopsphere.entity.Product;

@Component
public class ProductMapper {
    
    public ProductResponse toProductResponse(Product product)
   {
       return ProductResponse.builder()
                             .id(product.getId())
                             .name(product.getName())
                             .description(product.getDescription())
                             .price(product.getPrice())
                             .stock(product.getStock())
                             .brand(product.getBrand())
                             .active(product.getActive())
                             .imageUrl(product.getImageUrl())
                             .categoryId(product.getCategory().getId())
                             .categoryName(product.getCategory().getName())
                             .createdAt(product.getCreatedAt())
                             .updatedAt(product.getUpdatedAt())
                             .build();
   }

    public Product toProduct(ProductRequest request, Category category)
    {
         return Product.builder()
            .name(request.getName())
            .description(request.getDescription())
            .price(request.getPrice())
            .stock(request.getStock())
            .brand(request.getBrand())
            .imageUrl(request.getImageUrl())
            .active(true)
            .category(category)
            .build();
    }
}
