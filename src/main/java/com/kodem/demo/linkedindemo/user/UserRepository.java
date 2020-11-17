package com.kodem.demo.linkedindemo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    
    // Find by Username
    Optional<User> findByUsername(String username);
}
