package com.example.ecommerceapp.usecase.order;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.domain.repository.OrderRepository;
import com.example.ecommerceapp.domain.repository.ProductRepository;
import com.example.ecommerceapp.frameworks.kafka.KafkaProducerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateOrderUseCase {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducer;

    public CreateOrderUseCase(ProductRepository productRepository,
                              OrderRepository orderRepository,
                              KafkaProducerService kafkaProducer) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public Order execute(List<Long> productIds) {
        // Récupérer les produits valides
        List<Product> products = productIds.stream()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if (products.isEmpty()) {
            throw new IllegalArgumentException("La commande doit contenir au moins un produit");
        }

        // Créer la commande
        Order order = new Order();
        order.setProducts(products);
        order.setStatus(Order.OrderStatus.NON_PAYE);

        // Sauvegarder la commande
        Order savedOrder = orderRepository.save(order);

        // Envoyer un seul événement Kafka pour la commande entière
        kafkaProducer.sendOrderCreated(savedOrder);

        return savedOrder;
    }
}
