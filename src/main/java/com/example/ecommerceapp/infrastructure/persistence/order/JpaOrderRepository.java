package com.example.ecommerceapp.infrastructure.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaOrderRepository = accès direct à la table orders en BDD H2
public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
}
