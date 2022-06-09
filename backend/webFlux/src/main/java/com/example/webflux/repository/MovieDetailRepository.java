package com.example.webflux.repository;

import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.entity.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieDetailRepository extends JpaRepository<MovieDetail,Long> {
    List<MovieDetail> findAllByMovieOrderByEpisodeAsc(Movie movie);
}
