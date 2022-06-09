package com.example.webflux.repository;


import com.example.webflux.model.entity.SecureToken;
import com.example.webflux.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecureTokenRepository extends JpaRepository<SecureToken,Long> {

    SecureToken findByToken(String token);

    void removeByToken(String token);

    SecureToken findByUser(User user);
}
