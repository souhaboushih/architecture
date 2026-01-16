package com.example.ecommerceapp.usecase.order;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.repository.OrderRepository;
import com.example.ecommerceapp.frameworks.kafka.KafkaProducerService;

import java.util.Optional;

public class UpdateOrderStatusUseCase {

    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducer;

    public UpdateOrderStatusUseCase(OrderRepository orderRepository, KafkaProducerService kafkaProducer) {
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;}

    public Order execute(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);

        // Envoie un événement Kafka
        kafkaProducer.sendOrderUpdated(order); // tu peux créer cette méthode dans KafkaProducerService

        return updatedOrder;
    }

}
