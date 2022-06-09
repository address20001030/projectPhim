package com.example.webflux.controller;

import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CountryController extends BaseController{
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/public/country/getAll")
    public Mono<BaseResponse> getAllCountry(){
        return success(countryService.getAllCountry());
    }
}
