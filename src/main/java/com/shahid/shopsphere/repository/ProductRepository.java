package com.shahid.shopsphere.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shahid.shopsphere.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product>{

    Optional<Product> findByName(String name);
    List<Product> findByCategoryId(Long id);
    List<Product> findByActiveTrue();
    List<Product> findByNameContainingIgnoreCase(String Keyword);
    List<Product> findByPriceBetween(BigDecimal minPrice,BigDecimal maxPrice);
    @Modifying
@Query("""
    UPDATE Product p
    SET p.stock = p.stock - :quantity
    WHERE p.id = :productId
      AND p.stock >= :quantity
""")
int decreaseStock(
        @Param("productId") Long productId,
        @Param("quantity") Integer quantity
);
    @Modifying
@Query("""
    UPDATE Product p
    SET p.stock = p.stock + :quantity
    WHERE p.id = :productId
""")
int increaseStock(
        @Param("productId") Long productId,
        @Param("quantity") Integer quantity
);
}
