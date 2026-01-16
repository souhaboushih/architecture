package com.example.ecommerceapp.infrastructure.persistence.payment;

import com.example.ecommerceapp.domain.model.Payment;
import com.example.ecommerceapp.domain.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class
PaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository jpaPaymentRepository;

    public PaymentRepositoryAdapter(JpaPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    // Convertir domaine -> entity
    private PaymentEntity toEntity(Payment payment) {
        return new PaymentEntity(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus()
        );
    }

    // Convertir entity -> domaine
    private Payment toDomain(PaymentEntity entity) {
        return new Payment(
                entity.getId(),
                entity.getOrderId(),
                entity.getAmount(),
                entity.getStatus()
        );
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = toEntity(payment);
        PaymentEntity saved = jpaPaymentRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return jpaPaymentRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Payment> findAll() {
        return jpaPaymentRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaPaymentRepository.deleteById(id);
    }
}
