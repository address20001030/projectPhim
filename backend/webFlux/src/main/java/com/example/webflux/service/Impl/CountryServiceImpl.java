package com.example.webflux.service.Impl;

import com.example.webflux.mapper.country.CountryMapper;
import com.example.webflux.model.entity.Country;
import com.example.webflux.model.response.country.CountryResponse;
import com.example.webflux.repository.CountryRepository;
import com.example.webflux.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<CountryResponse> getAllCountry() {
        List<Country> countries = countryRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        return countryMapper.to(countries);
    }

}
