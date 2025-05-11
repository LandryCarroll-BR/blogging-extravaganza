package com.smartstore.backend.application.service;

import com.smartstore.backend.domain.model.Post;
import com.smartstore.backend.domain.port.PostRepository;
import com.smartstore.backend.web.controller.PostRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByAuthor(String email) {
        return postRepository.findByAuthorEmail(email);
    }

    public Post createPost(PostRequest postRequest, String authorEmail) {
        Post post = new Post(
                null, // id will be auto-generated
                postRequest.getTitle(),
                postRequest.getContent(),
                authorEmail,
                LocalDateTime.now()
        );

        return postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Optional<Post> updatePost(Long id, PostRequest updatedPost, String requesterEmail) {
        Optional<Post> existing = postRepository.findById(id);

        if (existing.isPresent() && existing.get().getAuthorEmail().equals(requesterEmail)) {
            Post post = new Post(
                    id,
                    updatedPost.getTitle(),
                    updatedPost.getContent(),
                    requesterEmail,
                    existing.get().getCreatedAt()
            );
            return Optional.of(postRepository.save(post));
        }

        return Optional.empty();
    }

    public boolean deletePost(Long id, String requesterEmail) {
        Optional<Post> existing = postRepository.findById(id);
        if (existing.isPresent() && existing.get().getAuthorEmail().equals(requesterEmail)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
