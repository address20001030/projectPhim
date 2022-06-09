package com.example.webflux.security.jwt.model;

public enum JwtClaimKey {
    ID("id"),
    USERNAME("username"),
    ROLE("role");

    private final String value;

    JwtClaimKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
