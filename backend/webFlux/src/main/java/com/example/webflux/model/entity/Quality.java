package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_quality;

    @Column
    private String name_quality;

    @OneToMany(mappedBy = "quality")
    private List<LinkMovie> linkMovies = new ArrayList<>();

}
