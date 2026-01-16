package com.example.ecommerceapp.adapters.rest.payment;

public class PaymentDTO {

    private String orderId;
    private double amount;
    private String status;

    public PaymentDTO(String orderId, double amount, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }

    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setStatus(String status) { this.status = status; }
}
