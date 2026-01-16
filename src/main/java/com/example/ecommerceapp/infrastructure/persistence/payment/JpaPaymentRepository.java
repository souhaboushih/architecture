package com.example.ecommerceapp.infrastructure.persistence.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long> {
    // Optional: findByOrderId, etc.
}
