package com.example.ecommerceapp.infrastructure.mapper;

import com.example.ecommerceapp.domain.model.User;
import com.example.ecommerceapp.infrastructure.persistence.user.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword()

        );
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }
}
