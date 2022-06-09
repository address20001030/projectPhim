package com.example.webflux.service;

import com.example.webflux.model.entity.Country;
import com.example.webflux.model.response.country.CountryResponse;

import java.util.List;
import java.util.Set;

public interface CountryService {
    List<CountryResponse> getAllCountry();
}
