package com.example.webflux.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    private boolean accountVerified;

    private boolean loginDisabled;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;

    @OneToMany(mappedBy = "user")
    private List<CommentSection> commentSections = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewSection> reviewSections = new ArrayList<>();

}
