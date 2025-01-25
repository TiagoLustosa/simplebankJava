package com.simplebank.data.repositories;

import com.simplebank.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findById(UUID id);
}
