package com.example.webflux.service.Impl;

import com.example.webflux.exception.BusinessCode;
import com.example.webflux.exception.BusinessException;
import com.example.webflux.mapper.moviedetail.MovieDetailResponseMapper;
import com.example.webflux.mapper.moviedetail.MovieDetailSaveMapper;
import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.entity.MovieDetail;
import com.example.webflux.model.request.moviedetail.MovieDetailRequest;
import com.example.webflux.model.request.moviedetail.MovieDetailUpdateRequest;
import com.example.webflux.model.response.moviedetail.MovieDetailResponse;
import com.example.webflux.repository.MovieDetailRepository;
import com.example.webflux.repository.MovieRepository;
import com.example.webflux.service.MovieDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {

    private final MovieDetailRepository movieDetailRepository;
    private final MovieDetailSaveMapper movieDetailSaveMapper;
    private final MovieDetailResponseMapper movieDetailResponseMapper;
    private final MovieRepository movieRepository;

    public MovieDetailServiceImpl(MovieDetailRepository movieDetailRepository, MovieDetailSaveMapper movieDetailSaveMapper, MovieDetailResponseMapper movieDetailResponseMapper, MovieRepository movieRepository) {
        this.movieDetailRepository = movieDetailRepository;
        this.movieDetailSaveMapper = movieDetailSaveMapper;
        this.movieDetailResponseMapper = movieDetailResponseMapper;
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieDetailResponse saveMovieDetail(MovieDetailRequest movieDetailRequest) {
     MovieDetail movieDetail = movieDetailSaveMapper.to(movieDetailRequest);
     Optional<Movie> movies = movieRepository.findById(movieDetailRequest.getId_movie());
     movieDetail.setMovie(movies.get());
     movieDetailRepository.save(movieDetail);
     return movieDetailResponseMapper.to(movieDetail);
    }

    @Override
    public MovieDetailResponse getMovieDetailById(long id_detail) {
        Optional<MovieDetail> result = movieDetailRepository.findById(id_detail);
        result.orElseThrow(()->new BusinessException(BusinessCode.MOVIE_DETAIL_NOT_FOUND));
        return movieDetailResponseMapper.to(result.get());
    }

    @Override
    public MovieDetailResponse updateMovieDetail(MovieDetailUpdateRequest movieDetailUpdateRequest, long id_detail) {
        Optional<MovieDetail> result = movieDetailRepository.findById(id_detail);
        result.orElseThrow(()->new BusinessException(BusinessCode.MOVIE_DETAIL_NOT_FOUND));
        MovieDetail movieDetail = result.get();
        movieDetail.setEpisode(movieDetailUpdateRequest.getEpisode());
        movieDetailRepository.save(movieDetail);
        return movieDetailResponseMapper.to(movieDetail);
    }

    //Lấy tất cả các tập phim bằng id của phim
    @Override
    public List<MovieDetailResponse> getAllMovieDetail(long mv_id) {
        Optional<Movie> result = movieRepository.findById(mv_id);
        result.orElseThrow(()->new BusinessException(BusinessCode.MOVIE_NOT_FOUND));
        List<MovieDetail> movieDetailList = movieDetailRepository.findAllByMovieOrderByEpisodeAsc(result.get());
        return movieDetailResponseMapper.to(movieDetailList);
    }


}
