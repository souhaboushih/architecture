package com.example.ecommerceapp.usecase.produit;

import com.example.ecommerceapp.domain.repository.ProductRepository;

public class DeleteProductUseCase {

    private final ProductRepository repository;

    public DeleteProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.deleteById(id);
    }
}
