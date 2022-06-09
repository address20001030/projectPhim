package com.example.webflux.model.response.commentsection;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentResponse {
    private String cmContent;
    private LocalDateTime createDate;
    private String username;
}
