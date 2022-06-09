package com.example.webflux.service;

import com.example.webflux.model.request.moviedetail.MovieDetailRequest;
import com.example.webflux.model.request.moviedetail.MovieDetailUpdateRequest;
import com.example.webflux.model.response.moviedetail.MovieDetailResponse;

import java.util.List;

public interface MovieDetailService {
    MovieDetailResponse saveMovieDetail(MovieDetailRequest movieDetailRequest);

    MovieDetailResponse getMovieDetailById(long id_detail);

    MovieDetailResponse updateMovieDetail(MovieDetailUpdateRequest movieDetailUpdateRequest, long id_detail);

    List<MovieDetailResponse> getAllMovieDetail(long mv_id);
}
