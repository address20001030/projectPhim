package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class ReviewSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rv;

    @Column
    private long rate;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;
}
