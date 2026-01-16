package com.example.ecommerceapp.usecase.produit;

import com.example.ecommerceapp.domain.exception.ProductNotFoundException;
import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.domain.repository.ProductRepository;

public class GetProductByIdUseCase {

    private final ProductRepository repository;

    public GetProductByIdUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}
