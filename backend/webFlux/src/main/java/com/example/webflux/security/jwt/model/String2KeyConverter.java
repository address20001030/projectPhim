package com.example.webflux.security.jwt.model;


import com.example.webflux.exception.BusinessCode;
import com.example.webflux.exception.BusinessException;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public final class String2KeyConverter {
    private final KeyFactory keyFactory;

    public String2KeyConverter() {
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(BusinessCode.INTERNAL_SERVER);
        }
    }

    public PrivateKey getPrivateKey(String privateKey) {

        try (PemReader reader = new PemReader(new StringReader(privateKey));) {
            byte[] content;
            PemObject pemObject = reader.readPemObject();
            if (Objects.nonNull(pemObject)) {
                content = pemObject.getContent();
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(content);
                return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            }
            throw new BusinessException(BusinessCode.INTERNAL_SERVER);
        } catch (IOException | InvalidKeySpecException e) {
            throw new BusinessException(BusinessCode.INTERNAL_SERVER);
        }
    }

    public PublicKey getPublicKey(String publicKey) {
        try (PemReader reader = new PemReader(new StringReader(publicKey));) {
            byte[] content;
            PemObject pemObject = reader.readPemObject();
            if (Objects.nonNull(pemObject)) {
                content = pemObject.getContent();
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(content);
                return keyFactory.generatePublic(x509EncodedKeySpec);
            }
            throw new BusinessException(BusinessCode.INTERNAL_SERVER);
        } catch (IOException | InvalidKeySpecException e) {
            throw new BusinessException(BusinessCode.INTERNAL_SERVER);
        }

    }
}
