package com.smartstore.backend.domain.port;

import com.smartstore.backend.domain.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    List<Post> findAll();
    List<Post> findByAuthorEmail(String email);
    void deleteById(Long id);
}
