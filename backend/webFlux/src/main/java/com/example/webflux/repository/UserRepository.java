package com.example.webflux.repository;

import com.example.webflux.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsernameAndPasswordAndAccountVerified(String username, String password, boolean isAccountVerified);

//    String findByEmail(String email);

    User findByUsername(String username);

    User findByEmail(String email);

    boolean findByAccountVerified(Optional<User> user);



}
