package com.example.webflux.service;

import com.example.webflux.model.request.commentsection.CommentFilterRequest;
import com.example.webflux.model.request.reviewsection.ReviewRequest;
import com.example.webflux.model.response.reviewsection.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewService {
    ReviewResponse saveReview(ReviewRequest reviewRequest);

    Page<ReviewResponse> getAllReview(long mv_id, int offset, int limit);
}
