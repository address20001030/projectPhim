package com.example.webflux.mapper.moviedetail;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.MovieDetail;
import com.example.webflux.model.request.moviedetail.MovieDetailUpdateRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface MovieDetailUpdateMapper extends Mapper<MovieDetail, MovieDetailUpdateRequest> {
}
