package com.example.ecommerceapp.frameworks.paypal;

import org.springframework.stereotype.Component;

@Component
public class PaypalClient {

    public boolean pay(double amount) {
        // simulation PayPal
        return amount > 0;
    }
}
