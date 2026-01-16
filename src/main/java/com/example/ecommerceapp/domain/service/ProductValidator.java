package com.example.ecommerceapp.domain.service;


import com.example.ecommerceapp.domain.model.Product;

public class ProductValidator {

    public void validate(Product product) {

        // règle métier 1 : nom obligatoire
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Le nom du produit est obligatoire");
        }

        // règle métier 2 : prix positif
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Le prix doit être positif");
        }

        // règle métier 3 : stock valide
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Le stock ne peut pas être négatif");
        }
    }
}
