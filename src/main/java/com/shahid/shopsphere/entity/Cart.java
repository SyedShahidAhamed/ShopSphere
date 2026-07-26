package com.shahid.shopsphere.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false,unique=true)
    private User user;

    @Column(precision=10,scale=2,nullable=false)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(
        mappedBy="cart",
        cascade=CascadeType.ALL,
        orphanRemoval=true,
        fetch=FetchType.LAZY
    )
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();
    
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
