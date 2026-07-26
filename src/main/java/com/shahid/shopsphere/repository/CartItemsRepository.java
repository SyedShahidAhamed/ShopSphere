package com.shahid.shopsphere.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shahid.shopsphere.entity.CartItem;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItem, Long>{
     
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartItem> findByCartId(Long cartId);
}
