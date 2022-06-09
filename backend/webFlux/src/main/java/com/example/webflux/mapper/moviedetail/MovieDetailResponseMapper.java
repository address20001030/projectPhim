package com.example.webflux.mapper.moviedetail;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.MovieDetail;
import com.example.webflux.model.response.moviedetail.MovieDetailResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface MovieDetailResponseMapper extends Mapper<MovieDetailResponse, MovieDetail> {
}
