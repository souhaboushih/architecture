package com.example.ecommerceapp.frameworks.kafka;

import com.example.ecommerceapp.domain.model.Product;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "product-created",
            groupId = "ecommerce-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(Product product) {
        System.out.println(" Product received from Kafka:");
        System.out.println("️ " + product.getName() + " | " + product.getPrice());
    }
}
