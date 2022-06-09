package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class LinkMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_link;

    @Column(columnDefinition = "text")
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_quality")
    private Quality quality;

    @ManyToOne
    @JoinColumn(name = "id_detail")
    private MovieDetail movieDetail;
}
