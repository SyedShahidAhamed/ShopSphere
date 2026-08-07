package com.shahid.shopsphere.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.shahid.shopsphere.entity.Category;
import com.shahid.shopsphere.entity.Product;


@DataJpaTest
public class ProductRepositoryTestWithH2 {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
     void shouldSaveProduct(){
    //array
    Category category = Category.builder().name("Mobiles")
                                 .description("A Brand New Mobiles Category").build();

   entityManager.persist(category);
   

    Product product =    Product.builder()                  
                                .name("iPhone 17")
                                .description("Latest Apple Phone")
                                .price(BigDecimal.valueOf(90000))
                                .stock(10)
                                .brand("Apple")
                                .imageUrl("iphone.jpg")
                                .category(category)
                                .build();

     
                                //act
        Product savedProduct =productRepository.save(product);


        //assert
        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());
        assertEquals("iPhone 17", savedProduct.getName());

    assertEquals("Apple", savedProduct.getBrand());

    assertEquals(category.getId(), savedProduct.getCategory().getId());


     }

     @Test
     void shouldFindProductByName(){
         // Arrange
    Category category = Category.builder()
            .name("Mobiles")
            .description("Mobile Category")
            .active(true)
            .build();

    category = entityManager.persistAndFlush(category);

    Product product = Product.builder()
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .active(true)
            .category(category)
            .build();

            entityManager.persistAndFlush(product);
            Optional<Product> productFound = productRepository.findByName(product.getName());

            assertTrue(productFound.isPresent());
            assertNotNull(product);
            assertEquals("iPhone 17",productFound.get().getName());
            assertEquals("Apple", productFound.get().getBrand());
            assertEquals(category.getId(), productFound.get().getCategory().getId());
     }

     @Test
     void shouldFindProductById()
     {
        //arrange
         Category category = Category.builder()
            .name("Mobiles")
            .description("Mobile Category")
            .active(true)
            .build();

    category = entityManager.persistAndFlush(category);

    Product product = Product.builder()
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .active(true)
            .category(category)
            .build();
            entityManager.persistAndFlush(product);
            Optional<Product> productFound = productRepository.findById(product.getId());

            assertTrue(productFound.isPresent());
            assertNotNull(product);
            assertEquals("iPhone 17",productFound.get().getName());
            assertEquals("Apple", productFound.get().getBrand());
            assertEquals(category.getId(), productFound.get().getCategory().getId());
     }

     @Test
     void shouldFindProductByActive(){
          //arrange
         Category category = Category.builder()
            .name("Mobiles")
            .description("Mobile Category")
            .active(true)
            .build();

    category = entityManager.persistAndFlush(category);

    Product product = Product.builder()
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .active(true)
            .category(category)
            .build();

            entityManager.persistAndFlush(product);

        List<Product> productFound = productRepository.findByActiveTrue();

        assertFalse(productFound.isEmpty());
        assertEquals(1, productFound.size());
        assertEquals("iPhone 17", productFound.get(0).getName());
            assertEquals("Apple", productFound.get(0).getBrand());
            assertEquals(category.getId(), productFound.get(0).getCategory().getId());
     }

     @Test
     void shouldUpdateProduct(){

    // Arrange
    Category category = Category.builder()
            .name("Mobiles")
            .description("Mobile Category")
            .active(true)
            .build();

    category = entityManager.persistAndFlush(category);

    Product product = Product.builder()
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .active(true)
            .category(category)
            .build();

    product = entityManager.persistAndFlush(product);
         // Act
    product.setName("Samsung S26");
    product.setBrand("Samsung");
    product.setPrice(BigDecimal.valueOf(85000));
 entityManager.persistAndFlush(product);
    Optional<Product> updatedProduct = productRepository.findById(product.getId());

     // Assert
    assertTrue(updatedProduct.isPresent());

    assertEquals("Samsung S26",
            updatedProduct.get().getName());

    assertEquals("Samsung",
            updatedProduct.get().getBrand());

    assertEquals(BigDecimal.valueOf(85000),
            updatedProduct.get().getPrice());
     }

     @Test
      void shouldDeleteProduct(){
        // Arrange
    Category category = Category.builder()
            .name("Mobiles")
            .description("Mobile Category")
            .active(true)
            .build();

    category = entityManager.persistAndFlush(category);

    Product product = Product.builder()
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .active(true)
            .category(category)
            .build();

       product=entityManager.persistAndFlush(product);
         
        productRepository.deleteById(product.getId());
 Optional<Product> deletedProduct = productRepository.findById(product.getId());

        assertFalse(deletedProduct.isPresent());


      }
}
