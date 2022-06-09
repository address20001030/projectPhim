package com.example.webflux.repository;

import com.example.webflux.model.entity.LinkMovie;
import com.example.webflux.model.entity.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LinkMovieRepository extends JpaRepository<LinkMovie,Long> {
    List<LinkMovie> findByMovieDetail(MovieDetail movieDetail);



}
