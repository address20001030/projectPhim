package com.example.webflux.mapper.country;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.Country;
import com.example.webflux.model.response.country.CountryResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CountryMapper extends Mapper<CountryResponse, Country> {
}
