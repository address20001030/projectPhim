package com.example.webflux.security.jwt;


import com.example.webflux.security.jwt.model.JwtClaimKey;
import com.example.webflux.security.jwt.model.JwtPayload;
import org.jose4j.jwt.consumer.InvalidJwtException;

import java.security.PublicKey;
import java.util.Map;

public final class TokenConsumer {
    private final String audience;
    private final JWTParser parser;


    public TokenConsumer(String audience, PublicKey publicKey) {
        this.audience = audience;
        parser = new JWTParser(publicKey);
    }

    public JwtPayload consume(String token) throws InvalidJwtException {
        Map<String, Object> claims = parser.parseToken(token, audience);

        JwtPayload jwtPayload = new JwtPayload();

        jwtPayload.setId((Long) claims.get(JwtClaimKey.ID.getValue()));
        jwtPayload.setUsername((String) claims.get(JwtClaimKey.USERNAME.getValue()));
        jwtPayload.setRole((String) claims.get(JwtClaimKey.ROLE.getValue()));

        return jwtPayload;
    }
}
