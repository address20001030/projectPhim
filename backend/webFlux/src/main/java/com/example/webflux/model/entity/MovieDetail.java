package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class MovieDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detail;

    @Column
    private int episode;

    @ManyToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;

    @OneToMany(mappedBy = "movieDetail")
    private List<LinkMovie> linkMovies = new ArrayList<>();

}
