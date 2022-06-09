package com.example.webflux.service;


import com.example.webflux.model.entity.SecureToken;

public interface SecureTokenService {

    SecureToken createSecureToken();

    void saveSecureToken(SecureToken token);

    SecureToken findByToken(String token);

    void removeToken(SecureToken token);

    void removeTokenByToken(String token);

}
