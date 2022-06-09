package com.example.webflux.model.request.movie;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MovieFilterSearchRequest {
    private long id;
    private String director;
    private String actor;
    private String img;
    private int min;
    private int year;
    private String keyWork;
    private int limit;
    private int offset;
}
