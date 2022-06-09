package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_country;

    @Column
    private String name;

    @ManyToMany(mappedBy = "countries")
    private Set<Movie> movies = new HashSet<>();

}
