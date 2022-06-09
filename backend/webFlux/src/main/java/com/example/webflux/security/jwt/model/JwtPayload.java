package com.example.webflux.security.jwt.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class JwtPayload {
    private long id;
    private String username;
    private String role;

}
