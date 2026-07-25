package com.shahid.shopsphere.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shahid.shopsphere.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    Optional<Product> findByName(String name);
    List<Product> findByCategoryId(Long id);
    List<Product> findByActiveTrue();
    List<Product> findByNameContainingIgnoreCase(String Keyword);
    List<Product> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);
    
}
