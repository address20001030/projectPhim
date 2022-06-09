package com.example.webflux.model.request.commentsection;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequest {
    private String cmContent;
    private long id;
    private long id_movie;
}
