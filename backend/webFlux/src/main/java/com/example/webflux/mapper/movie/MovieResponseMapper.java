package com.example.webflux.mapper.movie;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.response.movie.MovieResponse;
@org.mapstruct.Mapper(componentModel = "spring")
public interface MovieResponseMapper extends Mapper<MovieResponse, Movie> {
}
