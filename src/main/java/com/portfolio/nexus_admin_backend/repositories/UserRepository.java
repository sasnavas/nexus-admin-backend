package com.portfolio.nexus_admin_backend.repositories;

import com.portfolio.nexus_admin_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Senior Magic! Just by naming the method like this, Spring knows it must
    // do a "SELECT * FROM users WHERE email = ?"
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}