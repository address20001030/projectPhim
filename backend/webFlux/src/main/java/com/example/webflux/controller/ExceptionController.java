package com.example.webflux.controller;


import com.example.webflux.exception.BusinessException;
import com.example.webflux.model.response.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionController {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse> handleBusinessException(BusinessException exception) {
        HttpStatus status = exception.getErrorResponse().getStatus();

        return new ResponseEntity<>(new BaseResponse(exception.getErrorResponse()), status);
    }
}
