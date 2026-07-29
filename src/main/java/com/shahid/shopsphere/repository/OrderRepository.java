package com.shahid.shopsphere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shahid.shopsphere.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
    
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);

}
