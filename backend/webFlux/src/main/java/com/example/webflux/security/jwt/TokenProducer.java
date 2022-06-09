package com.example.webflux.security.jwt;


import com.example.webflux.security.jwt.model.JwtClaimKey;
import com.example.webflux.security.jwt.model.JwtPayload;
import org.jose4j.jwt.JwtClaims;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public final class TokenProducer {
    private final String issuer;
    private final String subject;
    private final String[] audiences;
    private final float expiration;
    private final float notBefore;
    private PrivateKey privateKey;
    private JWTIssuer tokenIssuer;

    public TokenProducer(String issuer, String subject, String[] audiences, float expiration, float notBefore, PrivateKey privateKey) {
        this.issuer = issuer;
        this.subject = subject;
        this.audiences = audiences;
        this.expiration = expiration;
        this.notBefore = notBefore;
        this.privateKey = privateKey;
        this.tokenIssuer = new JWTIssuer(privateKey);
    }

    public TokenProducer(String issuer, String subject, String[] audiences, float expiration, float notBefore, String privateKey) {
        this.issuer = issuer;
        this.subject = subject;
        this.audiences = audiences;
        this.expiration = expiration;
        this.notBefore = notBefore;
        this.tokenIssuer = new JWTIssuer(privateKey);
    }

    public String produce(Map<String, Object> claims) {
        JwtClaims jwtClaims = new JwtClaims();

        jwtClaims.setIssuer(issuer);
        jwtClaims.setSubject(subject);
        jwtClaims.setAudience(audiences);
        jwtClaims.setExpirationTimeMinutesInTheFuture(expiration);
        jwtClaims.setNotBeforeMinutesInThePast(notBefore);

        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            jwtClaims.setClaim(entry.getKey(), entry.getValue());
        }

        return tokenIssuer.createToken(jwtClaims);
    }

    public String token(JwtPayload jwtPayload) {
        return produce(genarateClaims(jwtPayload));
    }

    private Map<String, Object> genarateClaims(JwtPayload jwtPayload) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimKey.ID.getValue(), jwtPayload.getId());
        claims.put(JwtClaimKey.USERNAME.getValue(), jwtPayload.getUsername());
        claims.put(JwtClaimKey.ROLE.getValue(), jwtPayload.getRole());

        return claims;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }


}