package com.example.ecommerceapp.domain.model;

public class Payment {

    private Long id;
    private String orderId;
    private double amount;
    private String status;

    public Payment(Long id, String orderId, double amount, String status) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}
