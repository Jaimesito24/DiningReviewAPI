package com.jaime.review.repository;

import com.jaime.review.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Finds a user by their unique display name
    Optional<User> findByDisplayName(String displayName);

    // Checks if a user exists by their unique display name
    boolean existsByDisplayName(String displayName);
}
