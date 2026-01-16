package com.example.ecommerceapp.infrastructure.persistence.payment;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String status; // SUCCESS ou FAILED

    public PaymentEntity() {}

    public PaymentEntity(Long id, String orderId, double amount, String status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
}
