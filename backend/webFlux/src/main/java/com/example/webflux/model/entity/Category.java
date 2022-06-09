package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue
    private long id_category;

    @Column
    private String name;


    @OneToMany(mappedBy = "category")
    private List<Movie> movies = new ArrayList<>();
}
