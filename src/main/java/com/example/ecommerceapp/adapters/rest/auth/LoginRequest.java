package com.example.ecommerceapp.adapters.rest.auth;


public record LoginRequest(
        String email,
        String password
) {}
