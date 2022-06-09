package com.example.webflux.mapper.commentsection;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.CommentSection;
import com.example.webflux.model.response.commentsection.CommentResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CommentResponseMapper extends Mapper<CommentResponse, CommentSection> {
}
