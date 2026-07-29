package com.shahid.shopsphere.service;

import java.math.BigDecimal;

import com.shahid.shopsphere.dto.page.PageResponse;
import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.dto.product.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    PageResponse<ProductResponse> getAllProducts(
        int page,
        int size,
        String sortBy,
        String direction,
        String category,
        String brand,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String keyword);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}