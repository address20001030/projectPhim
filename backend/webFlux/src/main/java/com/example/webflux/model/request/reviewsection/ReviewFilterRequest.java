package com.example.webflux.model.request.reviewsection;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewFilterRequest {
    private long mv_id;
    private int limit;
    private int offset;
}
