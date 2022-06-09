package com.example.webflux.repository;

import com.example.webflux.model.entity.CommentSection;
import com.example.webflux.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<CommentSection,Long>, JpaSpecificationExecutor<CommentSection> {

}
