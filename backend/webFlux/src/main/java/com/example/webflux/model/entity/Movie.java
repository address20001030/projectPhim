package com.example.webflux.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_movie;

    @Column
    private String name;

    @Column(columnDefinition = "text")
    private String content;

    @Column(columnDefinition = "text")
    private String actor;

    @Column
    private String image;

    @Column
    private int time;

    @Column
    private int year;

    @Column
    private String director;

    @Column
    private int quantity;

    @Column
    private String imgQuality;


    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name="id_category")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="country_connective",
    joinColumns = @JoinColumn(name="cnmv_id", referencedColumnName = "id_movie"),
    inverseJoinColumns = @JoinColumn(name = "cnct_id", referencedColumnName = "id_country"))
    private Set<Country> countries = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private List<MovieDetail> movieDetails = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<CommentSection> commentSections = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<ReviewSection> reviewSections  = new ArrayList<>();
}
