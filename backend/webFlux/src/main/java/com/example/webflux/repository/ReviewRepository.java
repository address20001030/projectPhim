package com.example.webflux.repository;

import com.example.webflux.model.entity.ReviewSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewRepository extends JpaRepository<ReviewSection,Long>, JpaSpecificationExecutor<ReviewSection> {
}
