package com.example.ecommerceapp.usecase.order;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllOrdersUseCase {

    private final OrderRepository orderRepository;

    public GetAllOrdersUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> execute(Order.OrderStatus status) {

        List<Order> orders = orderRepository.findAll();

        if (status != null) {
            orders = orders.stream()
                    .filter(o -> o.getStatus() == status)
                    .collect(Collectors.toList());
        }

        return orders;
    }
}
