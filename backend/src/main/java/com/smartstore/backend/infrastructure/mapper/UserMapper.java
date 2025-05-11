package com.smartstore.backend.infrastructure.mapper;

import com.smartstore.backend.domain.model.User;
import com.smartstore.backend.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        System.out.println(user);
        return new UserEntity(user.getEmail(), user.getName(), user.getPicture());
    }

    public User toDomain(UserEntity entity) {
        System.out.println(entity);
        return new User(entity.getEmail(), entity.getName(), entity.getPicture());
    }
}

