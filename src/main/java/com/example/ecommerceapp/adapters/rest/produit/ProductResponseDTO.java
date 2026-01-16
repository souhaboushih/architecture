package com.example.ecommerceapp.adapters.rest.produit;



import java.time.LocalDateTime;

public class ProductResponseDTO {

    public Long id;
    public String name;
    public String description;
    public double price;
    public int stock;
    public String category;
    public LocalDateTime createdAt;
}
