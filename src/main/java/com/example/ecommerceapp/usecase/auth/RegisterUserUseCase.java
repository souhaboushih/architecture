package com.example.ecommerceapp.usecase.auth;


import com.example.ecommerceapp.domain.model.User;
import com.example.ecommerceapp.domain.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepository userRepository,
                               BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }
}
