package com.example.webflux.model.response.linkmovie;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkMovieResponse {
    private long id_link;
    private String url;
    private String name_quality;
}
