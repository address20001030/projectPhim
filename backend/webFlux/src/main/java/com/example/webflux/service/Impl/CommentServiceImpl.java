package com.example.webflux.service.Impl;

import com.example.webflux.mapper.commentsection.CommentResponseMapper;
import com.example.webflux.mapper.commentsection.CommentSaveMapper;
import com.example.webflux.model.entity.CommentSection;
import com.example.webflux.model.entity.Movie;
import com.example.webflux.model.entity.User;
import com.example.webflux.model.request.commentsection.CommentFilterRequest;
import com.example.webflux.model.request.commentsection.CommentRequest;
import com.example.webflux.model.response.commentsection.CommentResponse;
import com.example.webflux.repository.CommentRepository;
import com.example.webflux.repository.MovieRepository;
import com.example.webflux.repository.Specification.CommentSpecification;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentSaveMapper commentSaveMapper;
    private final CommentResponseMapper commentResponseMapper;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CommentSaveMapper commentSaveMapper, CommentResponseMapper commentResponseMapper, UserRepository userRepository, MovieRepository movieRepository) {
        this.commentRepository = commentRepository;
        this.commentSaveMapper = commentSaveMapper;
        this.commentResponseMapper = commentResponseMapper;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    //Lưu bình luận của user thông qua 1 request từ client
    @Override
    public CommentResponse saveComment(CommentRequest commentRequest) {
        CommentSection commentSection = commentSaveMapper.to(commentRequest);
        Optional<User> user = userRepository.findById(commentRequest.getId());
        commentSection.setUser(user.get());
        commentSection.setCreateDate(LocalDateTime.now());
        Optional<Movie> movie = movieRepository.findById(commentRequest.getId_movie());
        commentSection.setMovie(movie.get());
        commentRepository.save(commentSection);
        return commentResponseMapper.to(commentSection);
    }

    @Override
    public Page<CommentResponse> getAllComment(long mv_id, int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset,limit);
        Page<CommentSection> commentResponses = commentRepository.findAll(CommentSpecification.filterAll(mv_id),pageRequest);
        return commentResponses.map(t->{
            CommentResponse commentResponse = commentResponseMapper.to(t);
            Optional<User> user = userRepository.findById(t.getUser().getId());
            commentResponse.setUsername(user.get().getUsername());
            return commentResponse;
        });
    }

}

