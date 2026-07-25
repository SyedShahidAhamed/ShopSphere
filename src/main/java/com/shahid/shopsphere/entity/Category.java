package com.shahid.shopsphere.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name= "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Category name is required")
    @Size(max=100,message="Category name cannot exceed 100 characters")
    @Column(nullable=false,unique=true,length=100)
    private String name;

    @Size(max=255,message="Description cannot exceed 255 characters")
    @Column(length=255)
    private String description;

    @Builder.Default
    @Column(nullable=false)
    private Boolean active = true;
    
    @Column(nullable=false,updatable=false)
    private LocalDateTime createdAt;

     @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(
    mappedBy = "category",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL
)
@JsonManagedReference
@Builder.Default

private List<Product> products=new ArrayList<>();

    @PrePersist
    public void prePersist()
    {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate()
    {
        this.updatedAt = LocalDateTime.now();
    }

}
