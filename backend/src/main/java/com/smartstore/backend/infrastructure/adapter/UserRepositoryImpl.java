package com.smartstore.backend.infrastructure.adapter;

import com.smartstore.backend.domain.model.User;
import com.smartstore.backend.domain.port.UserRepository;
import com.smartstore.backend.infrastructure.entity.UserEntity;
import com.smartstore.backend.infrastructure.mapper.UserMapper;
import com.smartstore.backend.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findById(email)
                .map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity saved = jpaUserRepository.save(userMapper.toEntity(user));
        return userMapper.toDomain(saved);
    }
}

