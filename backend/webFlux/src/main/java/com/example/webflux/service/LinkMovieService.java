package com.example.webflux.service;

import com.example.webflux.model.request.linkmovie.LinkMovieRequest;
import com.example.webflux.model.response.linkmovie.LinkMovieResponse;

import java.util.List;

public interface LinkMovieService {
    LinkMovieResponse saveLinkMovie(LinkMovieRequest linkMovieRequest);

    LinkMovieResponse updateLinkMovie(LinkMovieRequest linkMovieRequest, long id_link);

    List<LinkMovieResponse> listLink(long id_detail);
}
