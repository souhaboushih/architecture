package com.example.ecommerceapp.frameworks.kafka;

import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.model.Payment;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "product-created";
    private static final String PAYMENT_TOPIC = "payment-processed";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductCreated(Product product) {
        kafkaTemplate.send(TOPIC, product);
        System.out.println(" Product sent to Kafka: " + product.getName());
    }
    public void sendOrderCreated(Order order) {
        kafkaTemplate.send("order-created", order);
        System.out.println("Nouvelle commande: " + order.getId());
    }
    public void sendOrderUpdated(Order order) {
        kafkaTemplate.send("order-updated", order);
        System.out.println("Changement de status de la commande id : " + order.getId() + " | Status: " + order.getStatus());
    }
    public void sendPaymentProcessed(Payment payment) {
        kafkaTemplate.send("payment-processed", payment);
        System.out.println("Payment de la commande: " + payment.getOrderId() + " | " + payment.getStatus());
    }




}


