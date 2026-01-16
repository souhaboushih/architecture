package com.example.ecommerceapp.infrastructure.mapper;

import com.example.ecommerceapp.domain.model.Payment;
import com.example.ecommerceapp.adapters.rest.payment.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus()
        );
    }

}
