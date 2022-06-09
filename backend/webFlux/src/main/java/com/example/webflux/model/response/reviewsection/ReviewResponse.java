package com.example.webflux.model.response.reviewsection;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReviewResponse {
    private long rate;
    private LocalDateTime createDate;
    private String username;
}
