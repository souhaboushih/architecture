package com.example.ecommerceapp.infrastructure.persistence.Product;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private LocalDateTime createdAt;

    protected ProductEntity() {
    }

    public ProductEntity(Long id,
                         String name,
                         String description,
                         double price,
                         int stock,
                         String category,
                         LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
