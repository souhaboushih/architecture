package com.example.ecommerceapp.domain.repository;

import com.example.ecommerceapp.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    // Sauvegarde ou met à jour un paiement
    Payment save(Payment payment);

    // Recherche un paiement par son id
    Optional<Payment> findById(Long id);

    // Retourne tous les paiements
    List<Payment> findAll();

    // Supprime un paiement par son id
    void deleteById(Long id);
}
