package com.example.webflux.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BusinessException extends RuntimeException {

    private transient ErrorResponse errorResponse;

    public BusinessException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
