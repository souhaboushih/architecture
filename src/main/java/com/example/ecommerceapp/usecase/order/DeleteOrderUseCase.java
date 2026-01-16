package com.example.ecommerceapp.usecase.order;

import com.example.ecommerceapp.domain.repository.OrderRepository;

public class DeleteOrderUseCase {

    private final OrderRepository orderRepository;

    public DeleteOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(Long id) {
        orderRepository.deleteById(id);
    }
}
