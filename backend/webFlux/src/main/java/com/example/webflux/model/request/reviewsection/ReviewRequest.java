package com.example.webflux.model.request.reviewsection;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewRequest {
    private long rate;
    private long id;
    private long id_movie;
}
