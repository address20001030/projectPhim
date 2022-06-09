package com.example.webflux.controller;


import com.example.webflux.exception.BusinessCode;
import com.example.webflux.model.response.BaseResponse;
import reactor.core.publisher.Mono;

public abstract class BaseController {
    public Mono<BaseResponse> success(){
        return Mono.just(new BaseResponse(BusinessCode.SUCCESS));
    }

    public Mono<BaseResponse> success(Object data){
        return Mono.just(new BaseResponse(BusinessCode.SUCCESS,data));
    }
}
