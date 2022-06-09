package com.example.webflux.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class CommentSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cm_id;

    @Column(columnDefinition = "text")
    private String cmContent;

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
