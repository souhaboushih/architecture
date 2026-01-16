package com.example.ecommerceapp.usecase.dashboard;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.repository.OrderRepository;
import com.example.ecommerceapp.adapters.rest.dashboard.DashboardDTO;

import java.util.List;

public class GetDashboardUseCase {

    private final OrderRepository orderRepository;

    public GetDashboardUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public DashboardDTO execute() {
        List<Order> orders = orderRepository.findAll();

        if (orders.isEmpty()) {
            throw new RuntimeException("Aucune commande trouvée pour le dashboard");
        }

        long totalOrders = orders.size();
        long paidOrders = orders.stream()
                .filter(o -> o.getStatus() == Order.OrderStatus.PAYE)
                .count();
        long unpaidOrders = orders.stream()
                .filter(o -> o.getStatus() == Order.OrderStatus.NON_PAYE)
                .count();
        double totalRevenue = orders.stream()
                .filter(o -> o.getStatus() == Order.OrderStatus.PAYE)
                .mapToDouble(Order::getTotal)
                .sum();

        return new DashboardDTO(totalOrders, paidOrders, unpaidOrders, totalRevenue);
    }
}
