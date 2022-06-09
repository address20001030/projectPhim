package com.example.webflux.controller;

import com.example.webflux.model.request.linkmovie.LinkMovieRequest;
import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.LinkMovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class LinkMovieController extends BaseController {
    private final LinkMovieService linkMovieService;

    public LinkMovieController(LinkMovieService linkMovieService) {
        this.linkMovieService = linkMovieService;
    }

    @PostMapping("/movie/link/save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> saveLink (@RequestBody LinkMovieRequest linkMovieRequest){
        return success(linkMovieService.saveLinkMovie(linkMovieRequest));
    }

    @GetMapping("/public/movie/links")
    public Mono<BaseResponse> getAllLinks (@RequestParam long idDetail){
        return success(linkMovieService.listLink(idDetail));
    }



}
