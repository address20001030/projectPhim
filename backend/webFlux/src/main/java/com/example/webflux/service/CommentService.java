package com.example.webflux.service;

import com.example.webflux.model.request.commentsection.CommentFilterRequest;
import com.example.webflux.model.request.commentsection.CommentRequest;
import com.example.webflux.model.response.commentsection.CommentResponse;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentResponse saveComment(CommentRequest commentRequest);

    Page<CommentResponse> getAllComment(long mv_id, int offset, int limit);
}
