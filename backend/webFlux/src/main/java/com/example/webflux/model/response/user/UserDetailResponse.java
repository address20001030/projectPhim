package com.example.webflux.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
    private long id;
    private String username;
    private String email;
    private boolean accountVerified;
}
