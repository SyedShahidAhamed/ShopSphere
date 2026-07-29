package com.shahid.shopsphere.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;


import com.shahid.shopsphere.entity.Product;

public class ProductSpecification {
    

    public static Specification<Product> hasCategory(String category)
    {
         return (root, query, cb) ->

                category == null || category.isBlank()

                        ? null

                        : cb.equal(
                                cb.lower(root.get("category").get("name")),
                                category.toLowerCase());
    }

    public static Specification<Product> hasBrand(String brand)
    {
        return (root,query,cb) ->
                                brand == null || brand.isBlank() 
                                ? null 
                                :cb.equal(
                                cb.lower(root.get("brand"))
                                , brand.toLowerCase()
                                );
    }

    public static Specification<Product> hasMinPrice(BigDecimal minPrice)
    {
        return (root,query,cb) ->
                                  minPrice == null
                                  ? null
                                  :cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

     public static Specification<Product> hasMaxPrice(BigDecimal maxPrice)
    {
        return (root,query,cb) ->
                                  maxPrice == null
                                  ? null
                                  :cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
    public static Specification<Product> hasKeyword(String keyword) {

        return (root, query, cb) ->

                keyword == null || keyword.isBlank()

                        ? null

                        : cb.like(
                                cb.lower(root.get("name")),
                                "%" + keyword.toLowerCase() + "%");
    }

    }

