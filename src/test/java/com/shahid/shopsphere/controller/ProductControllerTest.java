package com.shahid.shopsphere.controller;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahid.shopsphere.dto.page.PageResponse;
import com.shahid.shopsphere.dto.product.ProductRequest;
import com.shahid.shopsphere.dto.product.ProductResponse;
import com.shahid.shopsphere.security.JwtAuthenticationFilter;
import com.shahid.shopsphere.service.CustomUserDetailsService;
import com.shahid.shopsphere.service.JwtService;
import com.shahid.shopsphere.service.ProductService;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters=false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private CustomUserDetailsService userDetailsService;

    @Test
    @WithMockUser(username="admin",roles ="ADMIN")
    void shouldCreateProductSuccessfully() throws Exception {

        // Arrange
        ProductRequest request = ProductRequest.builder()
                .name("iPhone 17")
                .description("Latest Apple Phone")
                .price(BigDecimal.valueOf(90000))
                .stock(10)
                .brand("Apple")
                .imageUrl("iphone17.jpg")
                .categoryId(1L)
                .build();

        ProductResponse response = ProductResponse.builder()
                .id(1L)
                .name("iPhone 17")
                .description("Latest Apple Phone")
                .price(BigDecimal.valueOf(90000))
                .stock(10)
                .brand("Apple")
                .imageUrl("iphone17.jpg")
                .categoryId(1L)
                .categoryName("Mobiles")
                .active(true)
                .build();

        when(productService.createProduct(any(ProductRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/products")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("iPhone 17"))
                .andExpect(jsonPath("$.brand").value("Apple"))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.categoryName").value("Mobiles"))
                .andExpect(jsonPath("$.active").value(true)
                );

        // Verify
        verify(productService).createProduct(any(ProductRequest.class));
    }

    @Test
    @WithMockUser(username="admin",roles="ADMIN")
    void shouldReturnProductByIdSuccessfully() throws Exception{
       ProductResponse response = ProductResponse.builder()
            .id(1L)
            .name("iPhone 17")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .categoryId(1L)
            .categoryName("Mobiles")
            .active(true)
            .build();
             when(productService.getProductById(1L)).thenReturn(response);
        mockMvc.perform(get("/api/products/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("iPhone 17"))
                .andExpect(jsonPath("$.brand").value("Apple"))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.categoryName").value("Mobiles"))
                .andExpect(jsonPath("$.active").value(true));
                
    verify(productService).getProductById(1L);
    }

    @Test
@WithMockUser(username = "user", roles = "USER")
void shouldReturnAllProductsSuccessfully() throws Exception {

    // Product 1
    ProductResponse product1 = ProductResponse.builder()
            .id(1L)
            .name("iPhone 17")
            .brand("Apple")
            .categoryId(1L)
            .categoryName("Mobiles")
            .build();

    // Product 2
    ProductResponse product2 = ProductResponse.builder()
            .id(2L)
            .name("Samsung S25")
            .brand("Samsung")
            .categoryId(1L)
            .categoryName("Mobiles")
            .build();

    // Page Response
    PageResponse<ProductResponse> pageResponse =
            PageResponse.<ProductResponse>builder()
                    .content(List.of(product1, product2))
                    .page(0)
                    .size(10)
                    .totalElements(2)
                    .totalPages(1)
                    .first(true)
                    .last(true)
                    .build();

    when(productService.getAllProducts(
            anyInt(),
            anyInt(),
            anyString(),
            anyString(),
            any(),
            any(),
            any(),
            any(),
            any()))
            .thenReturn(pageResponse);

    mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1))
            .andExpect(jsonPath("$.content[0].name").value("iPhone 17"))
            .andExpect(jsonPath("$.content[1].id").value(2))
            .andExpect(jsonPath("$.content[1].name").value("Samsung S25"))
            .andExpect(jsonPath("$.page").value(0))
            .andExpect(jsonPath("$.size").value(10))
            .andExpect(jsonPath("$.totalElements").value(2))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.first").value(true))
            .andExpect(jsonPath("$.last").value(true));

    verify(productService).getAllProducts(
            anyInt(),
            anyInt(),
            anyString(),
            anyString(),
            any(),
            any(),
            any(),
            any(),
            any());
}
          @Test
          @WithMockUser(username="admin",roles="ADMIN")
           void shouldUpdateProductSuccessfully() throws Exception{
          

           // Arrange
        ProductRequest request = ProductRequest.builder()
                .name("iPhone 18")
                .description("Latest Apple Phone")
                .price(BigDecimal.valueOf(90000))
                .stock(10)
                .brand("Apple")
                .imageUrl("iphone17.jpg")
                .categoryId(1L)
                .build();

         ProductResponse response = ProductResponse.builder()
            .id(1L)
            .name("iPhone 18")
            .description("Latest Apple Phone")
            .price(BigDecimal.valueOf(90000))
            .stock(10)
            .brand("Apple")
            .imageUrl("iphone17.jpg")
            .categoryId(1L)
            .categoryName("Mobiles")
            .active(true)
            .build();
                when(productService.updateProduct(eq(1L),any(ProductRequest.class))).thenReturn(response);
             
                mockMvc.perform(put("/api/products/{id}",1L)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                       .andExpect(status().isOk())
                       .andExpect(jsonPath("$.id").value(1))
                       .andExpect(jsonPath("$.name").value("iPhone 18"))
                        .andExpect(jsonPath("$.brand").value("Apple"))
            .andExpect(jsonPath("$.categoryId").value(1))
            .andExpect(jsonPath("$.categoryName").value("Mobiles"))
            .andExpect(jsonPath("$.active").value(true));
       
            verify(productService).updateProduct(eq(1L), any(ProductRequest.class));
        
          }


          @Test
          @WithMockUser(username="admin",roles="ADMIN")
          void shouldDeleteProductSuccessfully() throws Exception{
                doNothing().when(productService).deleteProduct(1L);

                mockMvc.perform(delete("/api/products/{id}",1L))
                .andExpect(status().isNoContent());

                verify(productService).deleteProduct(1L);
          }


          @Test
          @WithMockUser(username="admin",roles="ADMIN")
          void shouldReturnBadRequestWhenRequestIsInvalid() throws JsonProcessingException, Exception
          {
                 // Arrange
        ProductRequest request = ProductRequest.builder()
            .name("")          // Invalid
            .description("")
            .price(BigDecimal.valueOf(-100))
            .stock(-10)
            .brand("")
            .imageUrl("")
            .build();

            mockMvc.perform(post("/api/products")
                             .contentType(APPLICATION_JSON)
                             .content(objectMapper.writeValueAsString(request)))
                              .andExpect(status().isBadRequest());

                             
                             
          }

}