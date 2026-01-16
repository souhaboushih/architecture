package com.example.ecommerceapp.infrastructure.persistence.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}
