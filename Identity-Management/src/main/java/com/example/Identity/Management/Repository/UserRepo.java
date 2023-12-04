package com.example.Identity.Management.Repository;

import com.example.Identity.Management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Boolean existsByEmailOrUsername(String email, String username);
    Boolean existsByAccountNumber(String AccountNumber);
    User findByAccountNumber(String AccountNumber);
    Optional<User> findByEmailOrUsername(String email, String username);
}
