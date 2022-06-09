package com.example.webflux.repository;


import com.example.webflux.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findAllByIdIn(List<Long> ids);
}
