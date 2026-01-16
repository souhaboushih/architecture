package com.example.ecommerceapp.usecase.payment;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.model.Payment;
import com.example.ecommerceapp.domain.repository.OrderRepository;
import com.example.ecommerceapp.domain.service.PaymentValidator;
import com.example.ecommerceapp.frameworks.kafka.KafkaProducerService;
import com.example.ecommerceapp.frameworks.paypal.PaypalClient;

import java.util.List;

public class ProcessPaymentUseCase {

    private final OrderRepository orderRepository;
    private final PaymentValidator paymentValidator;
    private final PaypalClient paypalClient;
    private final KafkaProducerService kafkaProducerService;

    public ProcessPaymentUseCase(OrderRepository orderRepository,
                                 PaymentValidator paymentValidator,
                                 PaypalClient paypalClient,
                                 KafkaProducerService kafkaProducerService) {
        this.orderRepository = orderRepository;
        this.paymentValidator = paymentValidator;
        this.paypalClient = paypalClient;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Payment execute(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if (order.getStatus() != Order.OrderStatus.NON_PAYE) {
            throw new IllegalStateException("La commande est déjà payée");
        }

        double amount = order.getTotal();
        Payment payment = new Payment(null, orderId.toString(), amount, "SUCCESS"); // ✅ id = null

        List<String> errors = paymentValidator.validate(payment);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Paiement invalide : " + errors);
        }

        paypalClient.pay(amount);

        order.setStatus(Order.OrderStatus.PAYE);
        orderRepository.save(order);

        kafkaProducerService.sendPaymentProcessed(payment); //  utiliser la bonne méthode

        return payment;
    }
}
