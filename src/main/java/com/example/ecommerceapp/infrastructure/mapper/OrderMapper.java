package com.example.ecommerceapp.infrastructure.mapper;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.infrastructure.persistence.order.OrderEntity;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getProducts().stream()
                        .map(ProductMapper::toEntity)
                        .collect(Collectors.toList()),
                order.getTotal(),
                OrderEntity.OrderStatus.valueOf(order.getStatus().name()),
                order.getCreatedAt()
        );
    }

    public static Order toDomain(OrderEntity entity) {
        return new Order(
                entity.getId(),
                entity.getProducts().stream()
                        .map(ProductMapper::toDomain)
                        .collect(Collectors.toList()),
                Order.OrderStatus.valueOf(entity.getStatus().name())
        );
    }
}
