package com.example.webflux.security;


import com.example.webflux.security.jwt.TokenConsumer;
import com.example.webflux.security.jwt.model.JwtPayload;
import lombok.AllArgsConstructor;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public final class TokenManager {
    private final TokenConsumer tokenConsumer;

    public UserAuthentication getAuthentication(String token) throws InvalidJwtException {
        UserAuthentication userAuthentication = null;
        if (token != null) {
            JwtPayload jwtPayload = this.tokenConsumer.consume(token);
            UserDetail userDetails = new UserDetail();
            userDetails.setUser(jwtPayload);
            userAuthentication = new UserAuthentication(userDetails);
        }

        return userAuthentication;
    }
}
