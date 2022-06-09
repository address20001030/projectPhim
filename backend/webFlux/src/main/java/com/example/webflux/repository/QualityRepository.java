package com.example.webflux.repository;

import com.example.webflux.model.entity.LinkMovie;
import com.example.webflux.model.entity.Quality;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QualityRepository extends JpaRepository<Quality,Long> {
    @Override
    List<Quality> findAll(Sort sort);

//    Quality findByLinkMovie(LinkMovie linkMovie);
}

