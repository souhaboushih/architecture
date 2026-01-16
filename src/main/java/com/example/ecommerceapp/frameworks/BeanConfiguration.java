package com.example.ecommerceapp.frameworks;

import com.example.ecommerceapp.domain.repository.ProductRepository;
import com.example.ecommerceapp.domain.repository.OrderRepository;
import com.example.ecommerceapp.domain.service.ProductValidator;
import com.example.ecommerceapp.domain.service.OrderValidator;
import com.example.ecommerceapp.domain.service.PaymentValidator;
import com.example.ecommerceapp.usecase.dashboard.GetDashboardUseCase;
import com.example.ecommerceapp.usecase.produit.*;
import com.example.ecommerceapp.usecase.order.*;
import com.example.ecommerceapp.frameworks.kafka.KafkaProducerService;
import com.example.ecommerceapp.usecase.payment.ProcessPaymentUseCase;
import com.example.ecommerceapp.frameworks.paypal.PaypalClient;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    // ===== Domain Services =====
    @Bean
    public ProductValidator productValidator() {
        return new ProductValidator();
    }

    @Bean
    public OrderValidator orderValidator() {
        return new OrderValidator();
    }
    @Bean
    public PaymentValidator paymentValidator() {
        return new PaymentValidator();
    }

    // ===== Product Use Cases =====
    @Bean
    public AddProductUseCase addProductUseCase(ProductRepository productRepository,
                                               ProductValidator productValidator,
                                               KafkaProducerService kafkaProducer) { // ajouter
        return new AddProductUseCase(productRepository, productValidator, kafkaProducer);
    }


    @Bean
    public GetProductByIdUseCase getProductByIdUseCase(ProductRepository productRepository) {
        return new GetProductByIdUseCase(productRepository);
    }



    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepository productRepository,
                                                     ProductValidator productValidator,
                                                     KafkaProducerService kafkaProducerService) {
        return new UpdateProductUseCase(productRepository, productValidator, kafkaProducerService);
    }


    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepository productRepository) {
        return new DeleteProductUseCase(productRepository);
    }

    // ===== Order Use Cases =====
    @Bean
    public CreateOrderUseCase createOrderUseCase(ProductRepository productRepository,
                                                 OrderRepository orderRepository,
                                                 KafkaProducerService kafkaProducerService) {
        return new CreateOrderUseCase(productRepository, orderRepository, kafkaProducerService);
    }


    @Bean
    public GetOrderByIdUseCase getOrderByIdUseCase(OrderRepository orderRepository) {
        return new GetOrderByIdUseCase(orderRepository);
    }
    @Bean
    public GetDashboardUseCase getDashboardUseCase(OrderRepository orderRepository) {
        return new GetDashboardUseCase(orderRepository);
    }
    @Bean
    public GetAllOrdersUseCase getAllOrdersUseCase(OrderRepository orderRepository) {
        return new GetAllOrdersUseCase(orderRepository);
    }

    @Bean
    public UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderRepository orderRepository,
                                                             KafkaProducerService kafkaProducerService) {
        return new UpdateOrderStatusUseCase(orderRepository, kafkaProducerService);
    }


    @Bean
    public DeleteOrderUseCase deleteOrderUseCase(OrderRepository orderRepository) {
        return new DeleteOrderUseCase(orderRepository);
    }
    // ===== Payment Use Case =====
    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(
            OrderRepository orderRepository,
            PaymentValidator paymentValidator,
            PaypalClient paypalClient,
            KafkaProducerService kafkaProducer
    ) {
        return new ProcessPaymentUseCase(
                orderRepository,
                paymentValidator,
                paypalClient,
                kafkaProducer
        );
    }










}
