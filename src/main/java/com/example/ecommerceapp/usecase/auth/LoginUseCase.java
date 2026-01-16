package com.example.ecommerceapp.usecase.auth;



import com.example.ecommerceapp.domain.model.User;
import com.example.ecommerceapp.domain.repository.UserRepository;

import com.example.ecommerceapp.frameworks.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUseCase(UserRepository userRepository,
                        BCryptPasswordEncoder passwordEncoder,
                        JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String execute(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Bad credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Bad credentials");
        }

        return jwtService.generateToken(user);
    }
}
