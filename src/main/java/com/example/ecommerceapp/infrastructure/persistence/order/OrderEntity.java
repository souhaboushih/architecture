package com.example.ecommerceapp.infrastructure.persistence.order;

import com.example.ecommerceapp.infrastructure.persistence.Product.ProductEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Chaque order contient plusieurs produits (Many-to-Many)
    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;

    private double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    protected OrderEntity() { } // requis par JPA

    public OrderEntity(Long id, List<ProductEntity> products, double total, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.products = products;
        this.total = total;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public List<ProductEntity> getProducts() { return products; }
    public double getTotal() { return total; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setProducts(List<ProductEntity> products) { this.products = products; }
    public void setTotal(double total) { this.total = total; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public enum OrderStatus { NON_PAYE, PAYE, LIVRE }
}
