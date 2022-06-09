package com.example.webflux.mapper.movie;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.request.movie.MovieSaveRequest;
@org.mapstruct.Mapper(componentModel = "spring")
public interface MovieSaveMapper extends Mapper<Movie, MovieSaveRequest> {
}
