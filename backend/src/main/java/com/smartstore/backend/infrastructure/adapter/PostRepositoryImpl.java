package com.smartstore.backend.infrastructure.adapter;

import com.smartstore.backend.domain.model.Post;
import com.smartstore.backend.domain.port.PostRepository;
import com.smartstore.backend.infrastructure.entity.PostEntity;
import com.smartstore.backend.infrastructure.mapper.PostMapper;
import com.smartstore.backend.infrastructure.repository.JpaPostRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final PostMapper postMapper;

    public PostRepositoryImpl(JpaPostRepository jpaPostRepository, PostMapper postMapper) {
        this.jpaPostRepository = jpaPostRepository;
        this.postMapper = postMapper;
    }

    @Override
    public Post save(Post post) {
        PostEntity saved = jpaPostRepository.save(postMapper.toEntity(post));
        return postMapper.toDomain(saved);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaPostRepository.findById(id).map(postMapper::toDomain);
    }

    @Override
    public List<Post> findAll() {
        return jpaPostRepository.findAll().stream().map(postMapper::toDomain).toList();
    }

    @Override
    public List<Post> findByAuthorEmail(String email) {
        return jpaPostRepository.findByAuthorEmail(email).stream().map(postMapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaPostRepository.deleteById(id);
    }
}
