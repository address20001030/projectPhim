package com.example.webflux.model.request.user;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String token;
    private String password;
    private String repeatPassword;
}
