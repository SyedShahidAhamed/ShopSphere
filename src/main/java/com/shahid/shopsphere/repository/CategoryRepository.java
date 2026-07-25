package com.shahid.shopsphere.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shahid.shopsphere.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    
    Optional<Category>findByName(String name);
}
