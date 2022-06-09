package com.example.webflux.model.request.commentsection;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentFilterRequest {
    private long mv_id;
    private int limit;
    private int offset;
}
