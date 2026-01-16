package com.example.ecommerceapp.adapters.rest.dashboard;

import com.example.ecommerceapp.usecase.dashboard.GetDashboardUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Dashboard",
        description = "API pour les statistiques globales des commandes et ventes"
)
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final GetDashboardUseCase getDashboardUseCase;

    public DashboardController(GetDashboardUseCase getDashboardUseCase) {
        this.getDashboardUseCase = getDashboardUseCase;
    }

    @Operation(
            summary = "Obtenir les statistiques globales du dashboard",
            description = "Retourne le nombre total de commandes, commandes payées/non payées et le chiffre d'affaires total"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dashboard récupéré avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DashboardDTO getDashboard() {
        return getDashboardUseCase.execute();
    }
}
