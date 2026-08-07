package com.shahid.shopsphere.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.entity.Category;
import com.shahid.shopsphere.entity.Product;
import com.shahid.shopsphere.repository.CategoryRepository;
import com.shahid.shopsphere.repository.ProductRepository;

import jakarta.transaction.Transactional;

@SpringBootTest

@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ProductIntegrationTest {
    
     
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
   

  @Test
  @WithMockUser(username="admin",roles="ADMIN")
    void shouldCreateProductSuccessfully() throws Exception{
    //arrange
    
    Category category = Category.builder()
                                .name("Mobiles")
                                .description("All Mobiles")
                                .active(true)
                                .build();

           Category   savedCategory = categoryRepository.save(category);


           ProductRequest request = ProductRequest.builder()
                                                    .name("iPhone 17")
                                                    .description("Latest Apple Phone")
                                                    .price(BigDecimal.valueOf(90000))
                                                    .stock(10)
                                                    .brand("Apple")
                                                    .imageUrl("iphone17.jpg")
                                                    .categoryId(savedCategory.getId())
                                                    .build();
                                    
                               mockMvc.perform(post("/api/products")
                                               .contentType(APPLICATION_JSON)
                                               .content(objectMapper.writeValueAsString(request))
                               )
                                               .andExpect(status().isCreated())
                                               .andExpect(jsonPath("$.name").value("iPhone 17"))
                                               .andExpect(jsonPath("$.brand").value("Apple"))
                                                .andExpect(jsonPath("$.categoryName").value("Mobiles"));

                            assertTrue(productRepository.findByName("iPhone 17").isPresent());

                            Product product = productRepository.findByName("iPhone 17").orElseThrow();

                            assertEquals("iPhone 17", product.getName());
                            assertEquals("Apple", product.getBrand());
                            assertEquals("Mobiles", product.getCategory().getName());
                            assertEquals(BigDecimal.valueOf(90000), product.getPrice());
                            
}
}
