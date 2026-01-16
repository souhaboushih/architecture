package com.example.ecommerceapp.usecase.produit;

import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.domain.repository.ProductRepository;
import com.example.ecommerceapp.domain.service.ProductValidator;
import com.example.ecommerceapp.frameworks.kafka.KafkaProducerService;
public class UpdateProductUseCase {

    private final ProductRepository repository;
    private final ProductValidator validator;
    private final KafkaProducerService kafkaProducer;

    public UpdateProductUseCase(ProductRepository repository,
                                ProductValidator validator, KafkaProducerService kafkaProducer) {
        this.repository = repository;
        this.validator = validator;
        this.kafkaProducer = kafkaProducer;
    }

    public Product execute(Product product) {
        // validation métier
        validator.validate(product);

        // sauvegarde
        Product updated = repository.save(product);

        // envoi à Kafka
        kafkaProducer.sendProductCreated(updated);

        return updated;
    }
}
