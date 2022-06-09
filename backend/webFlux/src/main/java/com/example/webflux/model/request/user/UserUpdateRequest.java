package com.example.webflux.model.request.user;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String username;
    private String password;
    private String email;
}
