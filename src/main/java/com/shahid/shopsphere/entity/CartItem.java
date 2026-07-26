package com.shahid.shopsphere.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartItem {
 @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="cart_id",nullable=false)
private Cart cart;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name = "product_id", nullable = false)
private Product product;

@Column(nullable=false)
@Builder.Default
private Integer quantity = 1;

 @Column(precision=10,scale=2,nullable=false)
private BigDecimal unitPrice;

 @Column(nullable=false,updatable=false)
private LocalDateTime createdAt;
   @Column(nullable=false)
private LocalDateTime updatedAt;
    

@PrePersist
    public void PrePersist()
    {
        this.createdAt = LocalDateTime.now();
       this. updatedAt =LocalDateTime.now();

    }

    @PreUpdate
    public void prePersist()
    {
       this. updatedAt =LocalDateTime.now();
    }
}
