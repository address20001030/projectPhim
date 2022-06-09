package com.example.webflux.exception;

public class UnkownIdentifierException extends Exception {

    public UnkownIdentifierException() {
    }

    public UnkownIdentifierException(String message) {
        super(message);
    }

    public UnkownIdentifierException(String message, Throwable cause) {
        super(message, cause);
    }
}
