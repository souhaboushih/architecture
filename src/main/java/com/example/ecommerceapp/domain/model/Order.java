package com.example.ecommerceapp.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    public enum OrderStatus { NON_PAYE, PAYE, LIVRE }

    private Long id;
    private List<Product> products;
    private double total;
    private OrderStatus status;
    private LocalDateTime createdAt; // 🔹 ajouter

    public Order() {
        this.createdAt = LocalDateTime.now(); // date par défaut
    }

    public Order(Long id, List<Product> products, OrderStatus status) {
        this.id = id;
        this.products = products;
        this.status = status;
        this.total = calculateTotal();
        this.createdAt = LocalDateTime.now(); // date par défaut
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) {
        this.products = products;
        this.total = calculateTotal();
    }

    public double getTotal() { return total; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; } // 🔹 getter
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; } // 🔹 setter
}
