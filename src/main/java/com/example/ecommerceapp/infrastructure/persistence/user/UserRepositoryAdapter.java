package com.example.ecommerceapp.infrastructure.persistence.user;

import com.example.ecommerceapp.domain.model.User;
import com.example.ecommerceapp.domain.repository.UserRepository;
import com.example.ecommerceapp.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaRepo;

    public UserRepositoryAdapter(JpaUserRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public User save(User user) {
        UserEntity saved = jpaRepo.save(UserMapper.toEntity(user));
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email)
                .map(UserMapper::toDomain);
    }
}
