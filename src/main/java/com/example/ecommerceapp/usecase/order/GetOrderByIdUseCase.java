package com.example.ecommerceapp.usecase.order;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.repository.OrderRepository;

import java.util.Optional;

public class GetOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public GetOrderByIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }
}
