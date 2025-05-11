package com.smartstore.backend.domain.port;

import com.smartstore.backend.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}

