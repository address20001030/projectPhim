package com.example.webflux.mapper.linkmovie;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.LinkMovie;
import com.example.webflux.model.response.linkmovie.LinkMovieResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface LinkMovieResponseMapper extends Mapper<LinkMovieResponse, LinkMovie> {
}
