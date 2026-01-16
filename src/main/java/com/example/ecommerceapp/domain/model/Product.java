package com.example.ecommerceapp.domain.model;

import java.time.LocalDateTime;

public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private LocalDateTime createdAt;

    public Product() {
    }
    public Product(Long id,
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
