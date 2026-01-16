package com.example.ecommerceapp.adapters.rest.order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    public Long id;
    public List<Long> productIds;
    public double total;
    public String status;
    public LocalDateTime createdAt;
    public OrderResponseDTO() {}

}
