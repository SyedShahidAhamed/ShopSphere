package com.shahid.shopsphere.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
import com.shahid.shopsphere.service.imp1.ProductServiceImp1;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImp1Test {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    // @Mock
    // private SortUtil sortUtil;
    @Mock
    private Product product;

    @Mock 
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImp1 productService;

    @Test
    void shouldCreateProduct(){
      
        //create product
        ProductRequest productRequest = ProductRequest.builder()
                                                      .name("iphone 17")
                                                      .description("Its a Brandnew Mobile with a high Quality Cam.")
                                                      .price(BigDecimal.valueOf(7500))
                                                      .stock(10)
                                                      .brand("Apple")
                                                      .imageUrl("https://Apple/iphone17.com")
                                                      .categoryId(1L)
                                                      .build();

       //create category
       Category category = new Category();
       category.setName("Mobiles");
       category.setId(1L);

       //product
       Product product = Product.builder()
                                .name("iphone 17")
                                .description("Latest Apple Phone")
                                .price(BigDecimal.valueOf(90000))
                                .stock(50)
                                .brand("Apple")
                                .imageUrl("https://Apple/iphone17.com")
                                .category(category)
                                .active(true)
                                .build();
                                            
        //product response
        ProductResponse productResponse = ProductResponse.builder()
                                                        .id(1L)
                                                        .name("iphone 17")
                                                        .categoryId(1L)
                                                        .categoryName("Mobiles")
                                                        .build();

      //tests for searching product
      // Product does not already exist
      when(productRepository.findByName(productRequest.getName()))
        .thenReturn(Optional.empty());
      //test for searching cat
      when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));
      //test for save
      when(productRepository.save(product)).thenReturn(product);
      //test productmappper to product
      when(productMapper.toProduct(productRequest, category)).thenReturn(product);
      //test productmapper to response
      when(productMapper.toProductResponse(product)).thenReturn(productResponse);

      //test service
      ProductResponse result = productService.createProduct(productRequest);
      //testing the result 
      assertNotNull(result);
      assertEquals(1L, result.getId());
      assertEquals("iphone 17", result.getName());
      assertEquals("Mobiles", result.getCategoryName());
    
      //verify
     
      verify(productRepository).findByName(productRequest.getName());
      verify(categoryRepository).findById(productRequest.getCategoryId());
      verify(productRepository).save(product);
      verify(productMapper).toProduct(productRequest, category);
       verify(productMapper).toProductResponse(product);
                                 
    }

     @Test
     void shouldThrowResourceNotFoundExceptionWhenProductAlreadyExist()
     {
          ProductRequest request = ProductRequest.builder()
        .name("iPhone 17")
        .description("Latest Apple Phone")
        .price(BigDecimal.valueOf(90000))
        .stock(50)
        .brand("Apple")
        .imageUrl("iphone17.jpg")
        .categoryId(1L)
        .build();

        Product existingProduct = Product.builder()
        .id(1L)
        .name("iPhone 17")
        .build();
         
        when(productRepository.findByName(request.getName())).thenReturn(Optional.ofNullable(existingProduct));

         assertThrows(ProductAlreadyExistsException.class, ()->productService.createProduct(request));
         verify(productRepository).findByName(request.getName());
         
         //verify
         verify(categoryRepository,never()).findById(anyLong());
         verify(productMapper,never()).toProductResponse(any());
         verify(productMapper,never()).toProduct(any(), any());
           verify(productRepository, never()).save(any());
            
     }
     
    @Test
void shouldThrowResourceNotFoundExceptionWhenCategoryNotFound() {

    ProductRequest request = ProductRequest.builder()
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(50)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .categoryId(1L)
            .build();

    when(productRepository.findByName(request.getName()))
            .thenReturn(Optional.empty());

    when(categoryRepository.findById(request.getCategoryId()))
            .thenReturn(Optional.empty());

    assertThrows(
            ResourceNotFoundException.class,
            () -> productService.createProduct(request)
    );

    verify(productRepository).findByName(request.getName());
    verify(categoryRepository).findById(request.getCategoryId());

    verify(productRepository, never()).save(any());
    verify(productMapper, never()).toProduct(any(), any());
    verify(productMapper, never()).toProductResponse(any());
}

      @Test
      
      void shouldReturnProductWhenIdExists(){
          

        //create category
       Category category = new Category();
       category.setName("Mobiles");
       category.setId(1L);

       //product
       Product product = Product.builder()
                               .id(1L)
                                .name("iphone 17")
                                .description("Latest Apple Phone")
                                .price(BigDecimal.valueOf(90000))
                                .stock(50)
                                .brand("Apple")
                                .imageUrl("https://Apple/iphone17.com")
                                .category(category)
                                .active(true)
                                .build();
                                //product response
        ProductResponse productResponse = ProductResponse.builder()
                                                        .id(1L)
                                                        .name("iphone 17")
                                                        .categoryId(1L)
                                                        .categoryName("Mobiles")
                                                        .build();

        when(productMapper.toProductResponse(product)).thenReturn(productResponse);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponse result = productService.getProductById(1L);
        
        assertEquals(1L, result.getId());
        assertEquals("iphone 17", result.getName());
        assertEquals(1L, result.getCategoryId());
        assertEquals("Mobiles", result.getCategoryName());
      verify(productRepository).findById(1L);
    verify(productMapper).toProductResponse(product);
      
 }

 @Test
void shouldThrowResourceNotFoundExceptionWhenProductNotFound() {
   
    when(productRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, ()->{productService.getProductById(1L);});
    verify(productRepository).findById(1L);

        verify(productMapper, never()).toProductResponse(any());
}

        @Test
         void shouldUpdateProductSuccesfully(){

               //create category
       Category category = new Category();
       category.setName("Mobiles");
       category.setId(1L);
            //product
       Product existingproduct = Product.builder()
                               .id(1L)
                                .name("iphone 17")
                                .description("Latest Apple Phone")
                                .price(BigDecimal.valueOf(90000))
                                .stock(50)
                                .brand("Apple")
                                .imageUrl("https://Apple/iphone17.com")
                                .category(category)
                                .active(true)
                                .build();

       //will be sent
       ProductRequest Updaterequest = ProductRequest.builder()
            .name("iphone 18")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(65000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .categoryId(1L)
            .build();
            

               //to be excepted
             ProductResponse productResponse = ProductResponse.builder()

                                                        .id(1L)

                                                        .name("iphone 18")

                                                        .price(BigDecimal.valueOf(65000))

                                                        .stock(10)

                                                        .categoryId(1L)

                                                        .categoryName("Mobiles")

                                                        .build();

            when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(existingproduct));
            when(productMapper.toProductResponse(existingproduct)).thenReturn(productResponse);
            when(productRepository.save(existingproduct)).thenReturn(existingproduct);
            when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));

            ProductResponse result = productService.updateProduct(1L,Updaterequest);

            //asserts
            assertNotNull(result);
            assertEquals("iphone 18", result.getName());
            assertEquals(BigDecimal.valueOf(65000), result.getPrice());
            assertEquals(10, result.getStock());

            //verify
            verify(productRepository).findById(1L);
            verify(productMapper).toProductResponse(existingproduct);
            verify(productRepository).save(existingproduct);
             verify(categoryRepository).findById(1L);
            verify(productMapper,never()).toProduct(any(), any());



                                    
         }
         @Test
         void shouldThrowResourceNotFoundExceptionWhenProductNotFoundWhileUpdating()
         {      ProductRequest Updaterequest = ProductRequest.builder()
            .name("iphone 18")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(65000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .categoryId(1L)
            .build();
            when(productRepository.findById(1L)).thenReturn(Optional.empty());
 
            assertThrows(ResourceNotFoundException.class,()->{productService.updateProduct(1L,Updaterequest);});

             verify(productRepository).findById(1L);
             
    verify(productRepository, never()).save(any());
    verify(productMapper, never()).toProduct(any(), any());
    verify(productMapper, never()).toProductResponse(any());
    verify(categoryRepository,never()).findById(anyLong());

         }
         @Test
         void shouldThrowResourceNotFoundExceptionWhenCategoryNotFoundWhileUpdating(){
            Category category = new Category();
            category.setId(1L);
            category.setName("Mobiles");
            //product
       Product existingproduct = Product.builder()
                               .id(1L)
                                .name("iphone 17")
                                .description("Latest Apple Phone")
                                .price(BigDecimal.valueOf(90000))
                                .stock(50)
                                .brand("Apple")
                                .imageUrl("https://Apple/iphone17.com")
                                .category(category)
                                .active(true)
                                .build();

            ProductRequest Updaterequest = ProductRequest.builder()
            .name("iphone 18")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(65000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .categoryId(1L)
            .build();
         when(productRepository.findById(1L)).thenReturn(Optional.of(existingproduct));
          when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
          assertThrows(ResourceNotFoundException.class,()-> productService.updateProduct(1L,Updaterequest));

    // Verify
    verify(productRepository).findById(1L);
    verify(categoryRepository).findById(1L);

    verify(productRepository, never()).save(any());
    verify(productMapper, never()).toProductResponse(any());
    verify(productMapper, never()).toProduct(any(), any());
         }
        @Test
         void shouldDeleteProductSuccessfully(){
              
         Category category = new Category();
            category.setId(1L);
            category.setName("Mobiles");
            //product
       Product existingproduct = Product.builder()
                               .id(1L)
                                .name("iphone 17")
                                .description("Latest Apple Phone")
                                .price(BigDecimal.valueOf(90000))
                                .stock(50)
                                .brand("Apple")
                                .imageUrl("https://Apple/iphone17.com")
                                .category(category)
                                .active(true)
                                .build();
  
            when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(existingproduct));
            
                   productService.deleteProduct(1L);
                  
                
                   verify(productRepository).findById(1L);
                    verify(productRepository, never()).save(any());
               verify(productMapper, never()).toProductResponse(any());
              verify(productMapper, never()).toProduct(any(), any());
              verify(categoryRepository,never()).findById(anyLong());
            
         }
         @Test
void shouldThrowResourceNotFoundExceptionWhenDeletingProduct() {

    when(productRepository.findById(1L))
            .thenReturn(Optional.empty());

    assertThrows(
            ResourceNotFoundException.class,
            () -> productService.deleteProduct(1L)
    );

    verify(productRepository).findById(1L);

    verify(productRepository, never()).delete(any(Product.class));
}
 //page
      @Test
void shouldThrowBadRequestForInvalidSortField() {

    assertThrows(
            BadRequestException.class,
            () -> productService.getAllProducts(
                    0,
                    10,
                    "invalidField",
                    "asc",
                    null,
                    null,
                    null,
                    null,
                    null
            )
    );

    verify(productRepository, never()).findAll(any(Specification.class), any(Pageable.class));
}       

         @Test
void shouldThrowBadRequestForInvalidDirectionField() {

    assertThrows(
            BadRequestException.class,
            () -> productService.getAllProducts(
                    0,
                    10,
                    "Price",
                    "wrong",
                    null,
                    null,
                    null,
                    null,
                    null
            )
    );

         }

          @Test
void shouldThrowBadRequestForInvalidPageNumberField() {

    assertThrows(
            BadRequestException.class,
            () -> productService.getAllProducts(
                    -1,
                    10,
                    "Price",
                    "asc",
                    null,
                    null,
                    null,
                    null,
                    null
            )
    );

        }

         @Test
void shouldThrowBadRequestForInvalidPageSizeField() {

    assertThrows(
            BadRequestException.class,
            () -> productService.getAllProducts(
                    0,
                    0,
                    "Price",
                    "asc",
                    null,
                    null,
                    null,
                    null,
                    null
            )
    );
}

@Test
void shouldReturnPagedProducts() {

    // Arrange
    Category category = new Category();
    category.setId(1L);
    category.setName("Mobiles");

    Product product = Product.builder()
            .id(1L)
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(50)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .category(category)
            .active(true)
            .build();

    ProductResponse response = ProductResponse.builder()
            .id(1L)
            .name("iPhone 17")
            .price(BigDecimal.valueOf(90000))
            .stock(50)
            .categoryId(1L)
            .categoryName("Mobiles")
            .build();

            Page<Product> page = new PageImpl<>(List.of(product));

            when(productRepository.findAll(any(Specification.class),any(Pageable.class))).thenReturn(page);
            when(productMapper.toProductResponse(product)).thenReturn(response);
            // Act
    PageResponse<ProductResponse> result =
            productService.getAllProducts(
                    0,
                    10,
                    "price",
                    "asc",
                    null,
                    null,
                    null,
                    null,
                    null
            );
            assertNotNull(result);
            assertEquals(1, result.getContent().size());
            assertEquals("iPhone 17", result.getContent().get(0).getName());
            assertEquals(1L, result.getContent().get(0).getId());
            assertEquals(1, result.getTotalElements());

            verify(productRepository).findAll(any(Specification.class),any(Pageable.class));
            verify(productMapper).toProductResponse(product);
            

}
@Test
void shouldFilterProductsSuccessfully() {

    Category category = new Category();
    category.setId(1L);
    category.setName("Mobiles");

    Product product = Product.builder()
            .id(1L)
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(50)
            .brand("Apple")
            .category(category)
            .active(true)
            .build();

    ProductResponse response = ProductResponse.builder()
            .id(1L)
            .name("iPhone 17")
            .categoryId(1L)
            .categoryName("Mobiles")
            .build();

    Page<Product> page = new PageImpl<>(List.of(product));

    when(productRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

    when(productMapper.toProductResponse(product))
            .thenReturn(response);

    PageResponse<ProductResponse> result =
            productService.getAllProducts(
                    0,
                    10,
                    "price",
                    "asc",
                    "Mobiles",
                    "Apple",
                    BigDecimal.valueOf(50000),
                    BigDecimal.valueOf(100000),
                    "iphone"
            );

    assertNotNull(result);

    assertEquals(1, result.getContent().size());

    assertEquals("iPhone 17",
            result.getContent().get(0).getName());

    verify(productRepository)
            .findAll(any(Specification.class), any(Pageable.class));

    verify(productMapper)
            .toProductResponse(product);
}
}
         
 
