package com.example.webflux.controller;

import com.example.webflux.model.request.commentsection.CommentFilterRequest;
import com.example.webflux.model.request.commentsection.CommentRequest;
import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class CommentSectionController extends BaseController{
        private final CommentService commentService;

    public CommentSectionController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/user/cmt/post")
    @PreAuthorize("hasAnyAuthority('USER') OR hasAnyAuthority('ADMIN')")
    Mono<BaseResponse> postComment(@RequestBody CommentRequest commentRequest){
        return success(commentService.saveComment(commentRequest));
    }

    @GetMapping("/public/movie/comment")
    Mono<BaseResponse> getComment(@RequestParam long mv_id, @RequestParam int offset, @RequestParam int limit){
        return success(commentService.getAllComment(mv_id,offset,limit));
    }
}
