package com.example.ecommerceapp.domain.service;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderValidator {

    private final ProductValidator productValidator = new ProductValidator();

    /**
     * Retourne la liste des erreurs de validation.
     * Si la liste est vide → commande valide
     */
    public List<String> validate(Order order) {
        List<String> errors = new ArrayList<>();

        // règle 1 : liste de produits non vide
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            errors.add("La commande doit contenir au moins un produit");
        }

        // règle 2 : chaque produit est valide
        if (order.getProducts() != null) {
            for (Product product : order.getProducts()) {
                try {
                    productValidator.validate(product);
                } catch (IllegalArgumentException e) {
                    errors.add("Produit invalide: " + e.getMessage());
                }
            }
        }

        // règle 3 : total positif
        if (order.getTotal() <= 0) {
            errors.add("Le total de la commande doit être positif");
        }

        // règle 4 : status non null
        if (order.getStatus() == null) {
            errors.add("Le statut de la commande est obligatoire");
        }

        return errors;
    }
}
