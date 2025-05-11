package com.smartstore.backend.infrastructure.repository;

import com.smartstore.backend.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {
}
