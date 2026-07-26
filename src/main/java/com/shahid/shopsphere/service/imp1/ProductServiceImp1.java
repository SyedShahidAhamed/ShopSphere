package com.shahid.shopsphere.service.imp1;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shahid.shopsphere.service.ProductService;

import lombok.RequiredArgsConstructor;

import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.dto.product.ProductResponse;
import com.shahid.shopsphere.entity.Category;
import com.shahid.shopsphere.entity.Product;
import com.shahid.shopsphere.exception.ProductAlreadyExistsException;
import com.shahid.shopsphere.exception.ResourceNotFoundException;
import com.shahid.shopsphere.repository.CategoryRepository;
import com.shahid.shopsphere.repository.ProductRepository;
@Service
@RequiredArgsConstructor
public class ProductServiceImp1 implements ProductService {
   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;

   public ProductResponse mapToProductResponse(Product product)
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

  @Override
public ProductResponse createProduct(ProductRequest request) {
 //find name
    productRepository.findByName(request.getName())
            .ifPresent(product -> {
                throw new ProductAlreadyExistsException("Product already exists.");
            });
  //find product category
    Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() ->
                    new ResourceNotFoundException("Category not found."));
 //buildproduct
    Product product = Product.builder()
            .name(request.getName())
            .description(request.getDescription())
            .price(request.getPrice())
            .stock(request.getStock())
            .brand(request.getBrand())
            .imageUrl(request.getImageUrl())
            .active(true)
            .category(category)
            .build();
 //now save
    Product savedProduct = productRepository.save(product);
 
    return mapToProductResponse(savedProduct);
} 
  @Override
   public List<ProductResponse> getAllProducts()
   {
      return productRepository.findAll().stream()
                                        .map(this::mapToProductResponse) 
                                        .toList();
   }

   @Override
   public  ProductResponse getProductById(Long id){

      Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Found With Id:" +id));

      return mapToProductResponse(product);
   }
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request){
      //fetch product
       Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Found With Id:" +id));
       //find category
       Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category Not Found With Id:" + id));

       //update
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setBrand(request.getBrand());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);
        //save new product
        Product updatedProduct = productRepository.save(product);
        return mapToProductResponse(updatedProduct);
    }

    @Override
public void deleteProduct(Long id) {

    Product product = productRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Product not found with id: " + id));

    productRepository.delete(product);
}
}
