package com.example.ecommerceapp.adapters.rest.payment;

import com.example.ecommerceapp.domain.model.Payment;
import com.example.ecommerceapp.infrastructure.mapper.PaymentMapper;
import com.example.ecommerceapp.usecase.payment.ProcessPaymentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Paiements",
        description = "API de paiement des commandes via PayPal"
)
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final PaymentMapper paymentMapper;

    public PaymentController(ProcessPaymentUseCase processPaymentUseCase,
                             PaymentMapper paymentMapper) {
        this.processPaymentUseCase = processPaymentUseCase;
        this.paymentMapper = paymentMapper;
    }

    // ================= PAY ORDER =================
    @Operation(
            summary = "Payer une commande",
            description = "Déclenche le paiement PayPal pour une commande existante à partir de son ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement effectué avec succès"),
            @ApiResponse(responseCode = "400", description = "Paiement invalide"),
            @ApiResponse(responseCode = "404", description = "Commande introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDTO pay(@PathVariable Long orderId) {
        Payment payment = processPaymentUseCase.execute(orderId);
        return paymentMapper.toDTO(payment);
    }
}
