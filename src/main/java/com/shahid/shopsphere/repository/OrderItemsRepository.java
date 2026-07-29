package com.shahid.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shahid.shopsphere.entity.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long>{
    
}
