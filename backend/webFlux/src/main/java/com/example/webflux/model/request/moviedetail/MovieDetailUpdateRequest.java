package com.example.webflux.model.request.moviedetail;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieDetailUpdateRequest {
    private int episode;
    private long id_movie;
}
