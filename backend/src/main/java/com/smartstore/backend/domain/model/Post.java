package com.smartstore.backend.domain.model;

import java.time.LocalDateTime;

public class Post {
    private final Long id;
    private final String title;
    private final String content;
    private final String authorEmail;
    private final LocalDateTime createdAt;

    public Post(Long id, String title, String content, String authorEmail, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthorEmail() { return authorEmail; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
