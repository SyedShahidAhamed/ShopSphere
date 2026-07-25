package com.shahid.shopsphere.service;

import java.util.List;

import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.dto.product.ProductResponse;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}