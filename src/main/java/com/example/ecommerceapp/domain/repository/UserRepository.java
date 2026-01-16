package com.example.ecommerceapp.domain.repository;



import com.example.ecommerceapp.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
}
