package com.example.ecommerceapp.infrastructure.mapper;



import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.infrastructure.persistence.Product.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getCreatedAt()
        );
    }

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock(),
                entity.getCategory(),
                entity.getCreatedAt()
        );
    }
}
