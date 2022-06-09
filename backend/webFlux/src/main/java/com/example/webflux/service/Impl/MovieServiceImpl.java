package com.example.webflux.service.Impl;

import com.example.webflux.exception.BusinessCode;
import com.example.webflux.exception.BusinessException;
import com.example.webflux.mapper.movie.MovieResponseMapper;
import com.example.webflux.mapper.movie.MovieSaveMapper;
import com.example.webflux.model.entity.Category;
import com.example.webflux.model.entity.Country;
import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.request.movie.MovieFilterSearchRequest;
import com.example.webflux.model.request.movie.MovieSaveRequest;
import com.example.webflux.model.response.movie.MovieResponse;
import com.example.webflux.repository.CategoryRepository;
import com.example.webflux.repository.CountryRepository;
import com.example.webflux.repository.MovieRepository;
import com.example.webflux.repository.Specification.MovieSpecification;
import com.example.webflux.service.MovieService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieSaveMapper movieSaveMapper;
    private final CountryRepository countryRepository;
    private final CategoryRepository categoryRepository;
    private final MovieResponseMapper movieResponseMapper;

    @Autowired
    public MovieServiceImpl( MovieRepository movieRepository, MovieSaveMapper movieSaveMapper, CountryRepository countryRepository, CountryRepository countryRepository1, CategoryRepository categoryRepository, MovieResponseMapper movieResponseMapper) {
        this.movieRepository = movieRepository;
        this.movieSaveMapper = movieSaveMapper;
        this.countryRepository = countryRepository1;
        this.categoryRepository = categoryRepository;
        this.movieResponseMapper = movieResponseMapper;
    }


    //Tạo mới một bộ phim
    @Override
    public MovieResponse save(MovieSaveRequest movieSaveRequest) {
        Movie movie = movieSaveMapper.to(movieSaveRequest);
        movie.setCreateDate(LocalDateTime.now());
        List<Country> countries = countryRepository.findAllById(movieSaveRequest.getId_countries());
        Optional<Category> category = categoryRepository.findById(movieSaveRequest.getId_cate());
        Set<Country> countrySet = new HashSet<>(countries);
        movie.setCountries(countrySet);
        movie.setCategory(category.get());
        try{
            movieRepository.save(movie);
        }catch (Exception e){
            throw new BusinessException(BusinessCode.ERROR_RESPONSE);
        }

        return movieResponseMapper.to(movie);
    }

    @Override
    public Page<MovieResponse> getAllMovie(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset,limit,
                Sort.by(Sort.Direction.DESC,"CreateDate").descending().and(Sort.by(Sort.Direction.DESC,"Year"))
                        .descending().and(Sort.by(Sort.Direction.ASC,"name")));
        Page<Movie> movies = movieRepository.findAll(pageable.previousOrFirst());
        return movies.map(movieResponseMapper::to);
    }

    //Cập nhật thông tin của bộ phim
    @Override
    public MovieResponse update(MovieSaveRequest movieSaveRequest, long id) {
        Optional<Movie> result = movieRepository.findById(id);
        result.orElseThrow(() -> new BusinessException(BusinessCode.MOVIE_NOT_FOUND));
        Movie movieUpdate = result.get();
        movieUpdate.setName(movieSaveRequest.getName());
        movieUpdate.setContent(movieSaveRequest.getContent());
        movieUpdate.setActor(movieSaveRequest.getActor());
        movieUpdate.setImage(movieSaveRequest.getImage());
        movieUpdate.setTime(movieSaveRequest.getTime());
        movieUpdate.setYear(movieSaveRequest.getYear());
        movieUpdate.setDirector(movieSaveRequest.getDirector());
        movieUpdate.setQuantity(movieSaveRequest.getQuantity());
        movieUpdate.setImgQuality(movieSaveRequest.getImgQuality());
        List<Country> countries = countryRepository.findAllById(movieSaveRequest.getId_countries());
        Optional<Category> category = categoryRepository.findById(movieSaveRequest.getId_cate());
        movieUpdate.setCategory(category.get());
        Set<Country> countrySet = new HashSet<>(countries);
        movieUpdate.setCountries(countrySet);
        return movieResponseMapper.to(movieRepository.save(movieUpdate));
    }

    //Lấy thông tin của phim thông qua id của bộ phim
    @Override
    public MovieResponse getMovieById(long id) {
        Optional<Movie> result = movieRepository.findById(id);
        result.orElseThrow(()-> new BusinessException(BusinessCode.MOVIE_NOT_FOUND));
        MovieResponse movieResponse = movieResponseMapper.to(result.get());
        String name_cate = result.get().getCategory().getName();
        movieResponse.setNameCate(name_cate);
        List<String> name_countries = result.get().getCountries().stream().map(Country::getName).collect(Collectors.toList());
        movieResponse.setNameCountry(name_countries);
        return movieResponse;
    }

    @Override
    public List<MovieResponse> getMovieByCategory(long id) {
        Optional<Category> result = categoryRepository.findById(id);
        result.orElseThrow(()->new BusinessException(BusinessCode.CATEGORY_NOT_FOUND));

        List<Movie> movies = movieRepository.findTop12ByCategoryOrderByCreateDateDesc(result.get());

        return movieResponseMapper.to(movies);
    }

    //Tìm kiếm phim qua bộ lọc MovieFilterSearchRequest
    @Override
    public Page<MovieResponse> getAll(MovieFilterSearchRequest filter) {
        PageRequest pageRequest = PageRequest.of(filter.getLimit(), filter.getOffset());
        Page<Movie> movies = movieRepository.findAll(MovieSpecification.filterAll(filter),pageRequest);
        return movies.map(movieResponseMapper::to);
    }


}
