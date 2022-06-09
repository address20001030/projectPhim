package com.example.webflux.model.request.movie;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieFilterRequest {
    private int limit;
    private int offset;
}
