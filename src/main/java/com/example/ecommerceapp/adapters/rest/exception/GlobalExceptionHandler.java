package com.example.ecommerceapp.adapters.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ===== 400 =====
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(400, ex.getMessage()));
    }

    // ===== 404 =====
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, ex.getMessage()));
    }

    // ===== 500 =====
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleServerError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(500, "Erreur interne du serveur"));
    }
}
