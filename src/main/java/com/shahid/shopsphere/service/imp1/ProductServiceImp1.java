package com.shahid.shopsphere.service.imp1;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shahid.shopsphere.dto.page.PageResponse;
import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.dto.product.ProductResponse;
import com.shahid.shopsphere.entity.Category;
import com.shahid.shopsphere.entity.Product;
import com.shahid.shopsphere.exception.BadRequestException;
import com.shahid.shopsphere.exception.ProductAlreadyExistsException;
import com.shahid.shopsphere.exception.ResourceNotFoundException;
import com.shahid.shopsphere.mapper.ProductMapper;
import com.shahid.shopsphere.repository.CategoryRepository;
import com.shahid.shopsphere.repository.ProductRepository;
import com.shahid.shopsphere.service.ProductService;
import com.shahid.shopsphere.specifications.ProductSpecification;
import com.shahid.shopsphere.util.SortUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductServiceImp1 implements ProductService {
   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;
   private  final ProductMapper productMapper;
   private final SortUtil sortUtil;
   

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
    Product product = productMapper.toProduct(request, category);
        
 //now save
    Product savedProduct = productRepository.save(product);
 
    return productMapper.toProductResponse(savedProduct);
} 


  @Override
   public PageResponse<ProductResponse> getAllProducts(
     int page,
        int size,
        String sortBy,
        String direction,
        String category,
        String brand,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String keyword)
   {
      sortBy = sortBy.trim();
direction = direction.trim().toLowerCase();
      //checksortby
      if(!sortUtil.ALLOWED_SORT_FIELDS.contains(sortBy.toLowerCase()))
      {
        
          throw new BadRequestException("Invalid sort field: " + sortBy);

      }
      //checkdirection
      if(!sortUtil.ALLOWED_DIRECTIONS.contains(direction.toLowerCase()))
      {
         throw new BadRequestException("Invalid direction field: "+ direction);
      }
      //check page req
      if (page < 0) {
            throw new BadRequestException("Page number cannot be negative.");
         }

         if (size <= 0 || size > 100) {
            throw new BadRequestException(
                     "Page size must be between 1 and 100.");
         }
             
             
         //sort logic
       Sort.Direction sortDirection = Sort.Direction.fromString(direction);
       Sort sort = Sort.by(sortDirection,sortBy);
            //page
            PageRequest pageable =PageRequest.of(page, size,sort);
            
            //specification
            Specification<Product> specification = Specification
                                                      .where(ProductSpecification.hasCategory(category)
                                                      .and(ProductSpecification.hasBrand(brand))
                                                      .and(ProductSpecification.hasMaxPrice(maxPrice))
                                                      .and(ProductSpecification.hasMinPrice(minPrice))
                                                      .and(ProductSpecification.hasKeyword(keyword)));
      Page<Product>productPage= productRepository.findAll(specification,pageable);



       //convert to list of product
       List<ProductResponse> products = productPage.getContent().stream().map(productMapper :: toProductResponse).toList();
       return PageResponse.<ProductResponse>builder()
                           .content(products)
                           .page(productPage.getNumber())
                                 .size(productPage.getSize())
                                 .totalElements(productPage.getTotalElements())
                                 .totalPages(productPage.getTotalPages())
                                 .first(productPage.isFirst())
                                 .last(productPage.isLast())
                                 .build();
   }

   @Override
   public  ProductResponse getProductById(Long id){

      Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Found With Id:" +id));

      return productMapper.toProductResponse(product);
   }
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request){
      //fetch product
       Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product Not Found With Id:" +id));
       //find category
       Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category Not Found With Id:" + request.getCategoryId()));
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
        return productMapper.toProductResponse(updatedProduct);
    }

    @Override
public void deleteProduct(Long id) {

    Product product = productRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Product not found with id: " + id));

    productRepository.delete(product);
}
}
