package com.smartstore.backend.infrastructure.repository;

import com.smartstore.backend.infrastructure.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByAuthorEmail(String email);
}
