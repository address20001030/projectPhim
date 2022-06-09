package com.example.webflux.controller;

import com.example.webflux.model.request.moviedetail.MovieDetailRequest;
import com.example.webflux.model.request.moviedetail.MovieDetailUpdateRequest;
import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.MovieDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class MovieDetailController extends BaseController {

    private final MovieDetailService movieDetailService;

    public MovieDetailController(MovieDetailService movieDetailService) {
        this.movieDetailService = movieDetailService;
    }

    @PostMapping("/movie/detail/save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> saveMovieDetail(@RequestBody MovieDetailRequest movieDetailRequest){
        return success(movieDetailService.saveMovieDetail(movieDetailRequest));
    }

    @PutMapping("/movie/detail/update/{id_detail}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> updateMovieDetail(@RequestBody MovieDetailUpdateRequest movieDetailUpdateRequest, @PathVariable long id_detail){
        return success(movieDetailService.updateMovieDetail(movieDetailUpdateRequest,id_detail));
    }

    @GetMapping("/public/movie/detail/{id}")
    public Mono<BaseResponse> getAll(@PathVariable long id){
        return success(movieDetailService.getAllMovieDetail(id));
    }


}
