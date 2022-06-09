package com.example.webflux.mapper.linkmovie;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.LinkMovie;
import com.example.webflux.model.request.linkmovie.LinkMovieRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface LinkMovieSaveMapper extends Mapper<LinkMovie,LinkMovieRequest> {
}
