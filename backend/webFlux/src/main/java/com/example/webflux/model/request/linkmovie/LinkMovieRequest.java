package com.example.webflux.model.request.linkmovie;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkMovieRequest {
    private String url;
    private long id_quality;
    private long id_detail;
}
