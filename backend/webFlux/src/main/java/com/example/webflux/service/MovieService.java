package com.example.webflux.service;


import com.example.webflux.model.request.movie.MovieFilterSearchRequest;
import com.example.webflux.model.request.movie.MovieSaveRequest;
import com.example.webflux.model.response.movie.MovieResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface MovieService {

    MovieResponse save(MovieSaveRequest movieSaveRequest);

    Page<MovieResponse> getAllMovie(int offset, int limit);

    MovieResponse update(MovieSaveRequest movieSaveRequest, long id);

    MovieResponse getMovieById(long id);

    List<MovieResponse> getMovieByCategory(long id);

    Page<MovieResponse> getAll(MovieFilterSearchRequest filter);


}
