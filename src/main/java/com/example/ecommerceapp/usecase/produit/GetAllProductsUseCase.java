package com.example.ecommerceapp.usecase.produit;

import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute(String name, String category, String sort) {

        List<Product> products = productRepository.findAll();

        // Filtrer par nom
        if (name != null && !name.isBlank()) {
            products = products.stream()
                    .filter(p ->
                            p.getName() != null &&
                                    p.getName().toLowerCase().contains(name.toLowerCase())
                    )
                    .collect(Collectors.toList());
        }

        //  Filtrer par catégorie
        if (category != null && !category.isBlank()) {
            products = products.stream()
                    .filter(p ->
                            p.getCategory() != null &&
                                    p.getCategory().equalsIgnoreCase(category)
                    )
                    .collect(Collectors.toList());
        }

        //  Trier par prix
        if ("price_asc".equalsIgnoreCase(sort)) {
            products.sort(Comparator.comparingDouble(Product::getPrice));
        }

        if ("price_desc".equalsIgnoreCase(sort)) {
            products.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        }

        return products;
    }
}
