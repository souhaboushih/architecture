package com.example.ecommerceapp.usecase.produit;

import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.domain.repository.ProductRepository;
import com.example.ecommerceapp.domain.service.ProductValidator;
import com.example.ecommerceapp.frameworks.kafka.KafkaProducerService;

public class AddProductUseCase {

    private final ProductRepository repository;
    private final ProductValidator validator;
    private final KafkaProducerService kafkaProducer;

    public AddProductUseCase(ProductRepository repository,
                             ProductValidator validator,KafkaProducerService kafkaProducer) {
        this.repository = repository;
        this.validator = validator;
        this.kafkaProducer = kafkaProducer;
    }

    public Product execute(Product product) {
        // validation métier
        validator.validate(product);

        // sauvegarde
        Product savedProduct = repository.save(product);

        // envoyer le produit créé dans Kafka
        kafkaProducer.sendProductCreated(savedProduct);

        return savedProduct;
    }
}
