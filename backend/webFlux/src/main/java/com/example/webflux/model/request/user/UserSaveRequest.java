package com.example.webflux.model.request.user;


import lombok.Data;

@Data
public class UserSaveRequest {
    private String username;
    private String password;
    private String email;


}
