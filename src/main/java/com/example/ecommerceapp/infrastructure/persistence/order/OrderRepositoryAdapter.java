package com.example.ecommerceapp.infrastructure.persistence.order;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.domain.repository.OrderRepository;
import com.example.ecommerceapp.infrastructure.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    public OrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public Order save(Order order) {
        // Mapper Order -> OrderEntity
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity saved = jpaOrderRepository.save(entity);
        return OrderMapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaOrderRepository.findById(id)
                .map(OrderMapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return jpaOrderRepository.findAll()
                .stream()
                .map(OrderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaOrderRepository.deleteById(id);
    }
}
