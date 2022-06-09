package com.example.webflux.model.request.movie;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class MovieSaveRequest {
    private String name;
    private String content;
    private String actor;
    private String image;
    private int time;
    private int year;
    private String director;
    private int quantity;
    private String imgQuality;
    private long id_cate;
    private List<Long> id_countries;
}
