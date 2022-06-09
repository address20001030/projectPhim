package com.example.webflux.repository;


import com.example.webflux.model.entity.Category;
import com.example.webflux.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>, JpaSpecificationExecutor<Movie> {
    List<Movie> findTop12ByCategoryOrderByCreateDateDesc(Category category);

}
