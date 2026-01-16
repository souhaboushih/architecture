package com.example.ecommerceapp.infrastructure.persistence.Product;

import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.domain.repository.ProductRepository;
import com.example.ecommerceapp.infrastructure.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
