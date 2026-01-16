package com.example.ecommerceapp.infrastructure.mapper;

import com.example.ecommerceapp.adapters.rest.dashboard.DashboardDTO;
import com.example.ecommerceapp.domain.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardMapper {

    public DashboardDTO toDTO(List<Order> orders) {
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
