package com.example.ecommerceapp.domain.service;

import com.example.ecommerceapp.domain.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentValidator {

    /**
     * Retourne la liste des erreurs de validation.
     * Si la liste est vide → paiement valide
     */
    public List<String> validate(Payment payment) {
        List<String> errors = new ArrayList<>();

        // règle 1 : montant positif
        if (payment.getAmount() <= 0) {
            errors.add("Le montant du paiement doit être positif");
        }

        // règle 2 : orderId non null ou vide
        if (payment.getOrderId() == null || payment.getOrderId().isEmpty()) {
            errors.add("L'identifiant de la commande est obligatoire");
        }

        // règle 3 : status valide (SUCCESS ou FAILED)
        if (payment.getStatus() == null ||
                (!payment.getStatus().equals("SUCCESS") && !payment.getStatus().equals("FAILED"))) {
            errors.add("Le statut du paiement doit être SUCCESS ou FAILED");
        }

        return errors;
    }
}
