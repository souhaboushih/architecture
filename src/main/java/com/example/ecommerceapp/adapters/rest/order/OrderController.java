package com.example.ecommerceapp.adapters.rest.order;

import com.example.ecommerceapp.domain.model.Order;
import com.example.ecommerceapp.usecase.order.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Commandes",
        description = "API de gestion des commandes (CRUD + filtrage par statut)"
)
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           GetOrderByIdUseCase getOrderByIdUseCase,
                           GetAllOrdersUseCase getAllOrdersUseCase,
                           UpdateOrderStatusUseCase updateOrderStatusUseCase,
                           DeleteOrderUseCase deleteOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.getAllOrdersUseCase = getAllOrdersUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
    }

    // ================= CREATE =================
    @Operation(summary = "Créer une commande", description = "Ajoute une nouvelle commande avec la liste des produits")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Commande créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO create(@RequestBody CreateOrderRequestDTO dto) {
        Order order = createOrderUseCase.execute(dto.productIds);
        return toResponse(order);
    }

    // ================= READ BY ID =================
    @Operation(summary = "Récupérer une commande par ID", description = "Retourne une commande à partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Commande trouvée"),
            @ApiResponse(responseCode = "404", description = "Commande introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public OrderResponseDTO getById(@PathVariable Long id) {
        return toResponse(getOrderByIdUseCase.execute(id));
    }

    // ================= READ ALL / FILTRAGE =================
    @Operation(summary = "Lister toutes les commandes", description = "Retourne la liste des commandes, filtrable par statut")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des commandes récupérée"),
            @ApiResponse(responseCode = "400", description = "Statut invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public List<OrderResponseDTO> getAll(@RequestParam(required = false) String status) {
        Order.OrderStatus filterStatus = null;

        if (status != null && !status.isBlank()) {
            try {
                filterStatus = Order.OrderStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Statut de commande invalide");
            }
        }

        List<Order> orders = getAllOrdersUseCase.execute(filterStatus);
        return orders.stream().map(this::toResponse).toList();
    }

    // ================= UPDATE STATUS =================
    @Operation(summary = "Mettre à jour le statut d'une commande", description = "Modifie le statut d'une commande existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Statut mis à jour"),
            @ApiResponse(responseCode = "404", description = "Commande introuvable"),
            @ApiResponse(responseCode = "400", description = "Statut invalide")
    })
    @PutMapping("/{id}/status")
    public OrderResponseDTO updateStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequestDTO dto) {
        try {
            Order.OrderStatus newStatus = Order.OrderStatus.valueOf(dto.status.toUpperCase());
            return toResponse(updateOrderStatusUseCase.execute(id, newStatus));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Statut de commande invalide");
        }
    }


    // ================= DELETE =================
    @Operation(summary = "Supprimer une commande", description = "Supprime une commande à partir de son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Commande supprimée"),
            @ApiResponse(responseCode = "404", description = "Commande introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteOrderUseCase.execute(id);
    }

    // ================= MAPPER =================
    private OrderResponseDTO toResponse(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.id = order.getId();
        dto.productIds = order.getProducts().stream().map(p -> p.getId()).toList();
        dto.total = order.getTotal();
        dto.status = order.getStatus().name();
        dto.createdAt = order.getCreatedAt();
        return dto;
    }
}
