package com.example.ecommerceapp.adapters.rest.auth;

import com.example.ecommerceapp.usecase.auth.LoginUseCase;
import com.example.ecommerceapp.usecase.auth.RegisterUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints d’authentification (register, login)")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase,
                          LoginUseCase loginUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    // ================= REGISTER =================
    @Operation(
            summary = "Créer un compte utilisateur",
            description = "Inscription d’un nouvel client avec email et mot de passe"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Email déjà utilisé ou données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest dto) {
        registerUserUseCase.execute(dto.email(), dto.password());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // ================= LOGIN =================
    @Operation(
            summary = "Connexion utilisateur",
            description = "Authentifie un client et retourne un token JWT"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Connexion réussie, token JWT retourné"),
            @ApiResponse(responseCode = "401", description = "Identifiants invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest dto) {
        String token = loginUseCase.execute(dto.email(), dto.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
