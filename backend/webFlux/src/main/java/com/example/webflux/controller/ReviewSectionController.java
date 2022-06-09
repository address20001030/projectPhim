package com.example.webflux.controller;

import com.example.webflux.model.request.commentsection.CommentFilterRequest;
import com.example.webflux.model.request.reviewsection.ReviewRequest;
import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class ReviewSectionController extends BaseController {
    private final ReviewService reviewService;

    public ReviewSectionController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/user/review/post")
    @PreAuthorize("hasAnyAuthority('USER') OR hasAnyAuthority('ADMIN')")
    public Mono<BaseResponse> postReview(@RequestBody ReviewRequest reviewRequest){
        return success(reviewService.saveReview(reviewRequest));
    }

    @GetMapping("/public/movie/review")
    public Mono<BaseResponse> getReview(@RequestParam long mv_id, @RequestParam int offset, @RequestParam int limit){
        return success(reviewService.getAllReview(mv_id,offset,limit));
    }
}
