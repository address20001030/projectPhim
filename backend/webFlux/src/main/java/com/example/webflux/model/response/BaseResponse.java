package com.example.webflux.model.response;

import com.example.webflux.exception.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String code;
    private String message;
    private int status;
    private Object data;

    public BaseResponse(ErrorResponse error, Object data) {
        this.code = error.getCode();
        this.message = error.getMessage();
        this.status = error.getStatus().value();
        this.data = data;
    }

    public BaseResponse(ErrorResponse error){
        this.code = error.getCode();
        this.message = error.getMessage();
        this.status = error.getStatus().value();
    }
}
