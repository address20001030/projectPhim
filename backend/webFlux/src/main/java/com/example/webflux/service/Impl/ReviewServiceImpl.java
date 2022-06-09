package com.example.webflux.service.Impl;

import com.example.webflux.mapper.reviewsection.ReviewResponseMapper;
import com.example.webflux.mapper.reviewsection.ReviewSaveMapper;
import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.entity.ReviewSection;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.request.reviewsection.ReviewRequest;
import com.example.webflux.model.response.reviewsection.ReviewResponse;
import com.example.webflux.repository.MovieRepository;
import com.example.webflux.repository.ReviewRepository;
import com.example.webflux.repository.Specification.ReviewSpecification;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewResponseMapper reviewResponseMapper;
    private final ReviewSaveMapper reviewSaveMapper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewResponseMapper reviewResponseMapper, ReviewSaveMapper reviewSaveMapper, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewResponseMapper = reviewResponseMapper;
        this.reviewSaveMapper = reviewSaveMapper;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    //Lưu đánh giá của user thông qua 1 request bên client
    @Override
    public ReviewResponse saveReview(ReviewRequest reviewRequest) {
        ReviewSection reviewSection = reviewSaveMapper.to(reviewRequest);
        Optional<User> result = userRepository.findById(reviewRequest.getId());
        Optional<Movie> results = movieRepository.findById(reviewRequest.getId_movie());
        reviewSection.setCreateDate(LocalDateTime.now());
        reviewSection.setUser(result.get());
        reviewSection.setMovie(results.get());
        reviewRepository.save(reviewSection);
        return reviewResponseMapper.to(reviewSection) ;
    }

    @Override
    public Page<ReviewResponse> getAllReview(long mv_id, int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset,limit);
        Page<ReviewSection> reviewSections = reviewRepository.findAll(ReviewSpecification.filterAll(mv_id),pageRequest);
        return reviewSections.map(t->{
            ReviewResponse response = reviewResponseMapper.to(t);
            Optional<User> result = userRepository.findById(t.getUser().getId());
            response.setUsername(result.get().getUsername());
            return response;
        });
    }


}
