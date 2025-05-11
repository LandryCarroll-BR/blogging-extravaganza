package com.smartstore.backend.web.controller;

import com.smartstore.backend.application.service.PostService;
import com.smartstore.backend.domain.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/mine")
    public List<Post> getMyPosts(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        return postService.getPostsByAuthor(email);
    }


    // Create a new post
    @PostMapping
    public Post createPost(@AuthenticationPrincipal Jwt jwt, @RequestBody PostRequest postRequest) {
        String authorEmail = jwt.getClaimAsString("email");
        return postService.createPost(postRequest, authorEmail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody PostRequest updatedPost
    ) {
        String email = jwt.getClaimAsString("email");
        return postService.updatePost(id, updatedPost, email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        boolean deleted = postService.deletePost(id, email);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
