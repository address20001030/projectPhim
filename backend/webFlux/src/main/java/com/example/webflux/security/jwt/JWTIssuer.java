package com.example.webflux.security.jwt;



import com.example.webflux.exception.BusinessCode;
import com.example.webflux.exception.BusinessException;
import com.example.webflux.security.jwt.model.String2KeyConverter;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;


public final class JWTIssuer {
    private final String2KeyConverter string2KeyConverter;
    private PrivateKey privateKey;

    public JWTIssuer() {
        string2KeyConverter = new String2KeyConverter();
    }

    public JWTIssuer(String privateKey) {
        this();
        setPrivateKey(privateKey);
    }

    public JWTIssuer(PrivateKey privateKey) {
        this();
        this.privateKey = privateKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = string2KeyConverter.getPrivateKey(privateKey);
    }

    public String createToken(JwtClaims claims) {
        JsonWebSignature jsonWebSignature = new JsonWebSignature();
        jsonWebSignature.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        jsonWebSignature.setPayload(claims.toJson());
        jsonWebSignature.setKey(privateKey);

        try {
            return jsonWebSignature.getCompactSerialization();
        } catch (JoseException e) {
            throw new BusinessException(BusinessCode.INTERNAL_SERVER);
        }
    }
}
