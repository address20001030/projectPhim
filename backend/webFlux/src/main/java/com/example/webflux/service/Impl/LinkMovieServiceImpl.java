package com.example.webflux.service.Impl;

import com.example.webflux.exception.BusinessCode;
import com.example.webflux.exception.BusinessException;
import com.example.webflux.mapper.linkmovie.LinkMovieResponseMapper;
import com.example.webflux.mapper.linkmovie.LinkMovieSaveMapper;
import com.example.webflux.model.entity.LinkMovie;
import com.example.webflux.model.entity.MovieDetail;
import com.example.webflux.model.entity.Quality;
import com.example.webflux.model.request.linkmovie.LinkMovieRequest;
import com.example.webflux.model.response.linkmovie.LinkMovieResponse;
import com.example.webflux.repository.LinkMovieRepository;
import com.example.webflux.repository.MovieDetailRepository;
import com.example.webflux.repository.QualityRepository;
import com.example.webflux.service.LinkMovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LinkMovieServiceImpl implements LinkMovieService {
    private final LinkMovieRepository linkMovieRepository;
    private final LinkMovieSaveMapper linkMovieSaveMapper;
    private final LinkMovieResponseMapper linkMovieResponseMapper;
    private final MovieDetailRepository movieDetailRepository;
    private final QualityRepository qualityRepository;

    public LinkMovieServiceImpl(LinkMovieRepository linkMovieRepository, LinkMovieSaveMapper linkMovieSaveMapper, LinkMovieResponseMapper linkMovieResponseMapper, MovieDetailRepository movieDetailRepository, QualityRepository qualityRepository) {
        this.linkMovieRepository = linkMovieRepository;
        this.linkMovieSaveMapper = linkMovieSaveMapper;
        this.linkMovieResponseMapper = linkMovieResponseMapper;
        this.movieDetailRepository = movieDetailRepository;
        this.qualityRepository = qualityRepository;
    }

    @Override
    public LinkMovieResponse saveLinkMovie(LinkMovieRequest linkMovieRequest) {
        LinkMovie linkMovie = linkMovieSaveMapper.to(linkMovieRequest);
        Optional<Quality> qualities = qualityRepository.findById(linkMovieRequest.getId_quality());
        linkMovie.setQuality(qualities.get());
        Optional<MovieDetail> movieDetail = movieDetailRepository.findById(linkMovieRequest.getId_detail());
        linkMovie.setMovieDetail(movieDetail.get());
        linkMovieRepository.save(linkMovie);
        return linkMovieResponseMapper.to(linkMovie);
    }

    @Override
    public LinkMovieResponse updateLinkMovie(LinkMovieRequest linkMovieRequest, long id_link) {
        Optional<LinkMovie> result = linkMovieRepository.findById(id_link);

        LinkMovie linkMovie = result.get();
        linkMovie.setUrl(linkMovieRequest.getUrl());
        Optional<MovieDetail> movieDetail = movieDetailRepository.findById(linkMovieRequest.getId_detail());
        linkMovie.setMovieDetail(movieDetail.get());
        Optional<Quality> quality = qualityRepository.findById(linkMovieRequest.getId_quality());
        linkMovie.setQuality(quality.get());
        linkMovieRepository.save(linkMovie);
        return linkMovieResponseMapper.to(linkMovie);
    }

    @Override
    public List<LinkMovieResponse> listLink(long id_detail) {
        Optional<MovieDetail> movieDetail = movieDetailRepository.findById(id_detail);
        movieDetail.orElseThrow(()->new BusinessException(BusinessCode.MOVIE_DETAIL_NOT_FOUND));
        List<LinkMovie> listLink = linkMovieRepository.findByMovieDetail(movieDetail.get());
        return linkMovieResponseMapper.to(listLink);
    }
}
