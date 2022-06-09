package com.example.webflux.controller;

import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.QualityService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class QualityController extends BaseController {
    private final QualityService qualityService;

    public QualityController(QualityService qualityService) {
        this.qualityService = qualityService;
    }

    @GetMapping("/public/quality/getAll")
    public Mono<BaseResponse> getAllQuality(){
        return success(qualityService.getAllQuality());
    }

//    @GetMapping("/public/quality/{id}")
//    public Mono<BaseResponse> getName(@PathVariable long id){
//        return success(qualityService.getName(id));
//    }
}
