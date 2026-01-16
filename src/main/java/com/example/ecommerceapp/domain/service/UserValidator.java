package com.example.ecommerceapp.domain.service;



import com.example.ecommerceapp.domain.model.User;

public class UserValidator {

    public void validate(User user) {

        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }

        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email invalide");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Mot de passe trop court");
        }
    }
}
