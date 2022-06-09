package com.example.webflux.controller;

import com.example.webflux.model.request.movie.MovieFilterSearchRequest;
import com.example.webflux.model.request.movie.MovieSaveRequest;
import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.MovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class FilmController extends BaseController{

    private final MovieService movieService;


    public FilmController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movie/upload")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> uploadMovie(@RequestBody MovieSaveRequest movieSaveRequest){
        return success(movieService.save(movieSaveRequest));
    }

    @GetMapping("/public/movie/getAll")
    public Mono<BaseResponse> getAllMovie(@RequestParam int offset, @RequestParam int limit){
        return success(movieService.getAllMovie(offset,limit));
    }

    @PutMapping ("/movie/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> updateMovie(@RequestBody MovieSaveRequest movieSaveRequest, @PathVariable long id){
        return success(movieService.update(movieSaveRequest,id));
    }

    @GetMapping("/public/movie/{id}")
    public Mono<BaseResponse> findMovie(@PathVariable long id){
        return success(movieService.getMovieById(id));
    }

    @GetMapping("/public/movie/category/{id}")
    public Mono<BaseResponse> findMovieByCategoryId(@PathVariable long id){
        return success(movieService.getMovieByCategory(id));
    }

    @GetMapping("/public/movie/search")
    public Mono<BaseResponse> findMovieAll(@RequestBody MovieFilterSearchRequest movieFilterSearchRequest){
        return success(movieService.getAll(movieFilterSearchRequest));
    }

}
