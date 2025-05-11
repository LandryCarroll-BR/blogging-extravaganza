package com.smartstore.backend.infrastructure.mapper;

import com.smartstore.backend.domain.model.Post;
import com.smartstore.backend.infrastructure.entity.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostEntity toEntity(Post post) {
        return new PostEntity(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthorEmail(),
                post.getCreatedAt()
        );
    }

    public Post toDomain(PostEntity entity) {
        return new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthorEmail(),
                entity.getCreatedAt()
        );
    }
}
