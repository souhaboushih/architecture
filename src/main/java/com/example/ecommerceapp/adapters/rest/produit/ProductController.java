package com.example.ecommerceapp.adapters.rest.produit;

import com.example.ecommerceapp.domain.model.Product;
import com.example.ecommerceapp.usecase.produit.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "Produits",
        description = "API de gestion des produits (CRUD complet)"
)
@RestController
@RequestMapping("/products")
public class ProductController {

    private final AddProductUseCase addProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(AddProductUseCase addProductUseCase,
                             GetProductByIdUseCase getProductByIdUseCase,
                             GetAllProductsUseCase getAllProductsUseCase,
                             UpdateProductUseCase updateProductUseCase,
                             DeleteProductUseCase deleteProductUseCase) {
        this.addProductUseCase = addProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    // ================= CREATE =================
    @Operation(
            summary = "Créer un produit",
            description = "Ajoute un nouveau produit dans le catalogue"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produit créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO create(@RequestBody CreateProductRequestDTO dto) {

        Product product = new Product(
                null,
                dto.name,
                dto.description,
                dto.price,
                dto.stock,
                dto.category,
                LocalDateTime.now()
        );

        return toResponse(addProductUseCase.execute(product));
    }

    // ================= READ BY ID =================
    @Operation(
            summary = "Récupérer un produit par ID",
            description = "Retourne un produit à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produit trouvé"),
            @ApiResponse(responseCode = "404", description = "Produit introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return toResponse(getProductByIdUseCase.execute(id));
    }

    // ================= READ ALL =================
    @Operation(
            summary = "Lister tous les produits",
            description = "Retourne la liste complète des produits"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des produits récupérée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })

    @GetMapping
    public List<ProductResponseDTO> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false, defaultValue = "asc") String sort
    ) {
        return getAllProductsUseCase.execute(name, category, sort)
                .stream()
                .map(this::toResponse)
                .toList();
    }


    // ================= UPDATE =================
    @Operation(
            summary = "Modifier un produit",
            description = "Met à jour les informations d’un produit existant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produit mis à jour"),
            @ApiResponse(responseCode = "404", description = "Produit introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PutMapping("/{id}")
    public ProductResponseDTO update(
            @PathVariable Long id,
            @RequestBody UpdateProductRequestDTO dto) {

        Product product = new Product(
                id,
                dto.name,
                dto.description,
                dto.price,
                dto.stock,
                dto.category,
                null
        );

        return toResponse(updateProductUseCase.execute(product));
    }

    // ================= DELETE =================
    @Operation(
            summary = "Supprimer un produit",
            description = "Supprime un produit à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produit supprimé"),
            @ApiResponse(responseCode = "404", description = "Produit introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteProductUseCase.execute(id);
    }

    // ================= MAPPER =================
    private ProductResponseDTO toResponse(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.description = product.getDescription();
        dto.price = product.getPrice();
        dto.stock = product.getStock();
        dto.category = product.getCategory();
        dto.createdAt = product.getCreatedAt();
        return dto;
    }
}
