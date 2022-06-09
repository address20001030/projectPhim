package com.example.webflux.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class SecureToken {
    @Id
    @GeneratedValue
    private long token_id;

    @Column(unique = true)
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timestamp;

    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;


    @Transient
    private boolean isExpired;


    public boolean isExpired(){
        return getExpireAt().isBefore(LocalDateTime.now());
    }
}
