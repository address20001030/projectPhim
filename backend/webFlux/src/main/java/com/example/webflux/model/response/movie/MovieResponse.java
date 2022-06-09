package com.example.webflux.model.response.movie;


import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {
    private long id_movie;
    private String name;
    private String content;
    private String actor;
    private String image;
    private int time;
    private int year;
    private String director;
    private int quantity;
    private String imgQuality;
    private String nameCate;
    private transient List<String> nameCountry;
}
