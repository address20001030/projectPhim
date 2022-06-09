package com.example.webflux.mapper.commentsection;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.CommentSection;
import com.example.webflux.model.request.commentsection.CommentRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CommentSaveMapper extends Mapper<CommentSection, CommentRequest> {
}
