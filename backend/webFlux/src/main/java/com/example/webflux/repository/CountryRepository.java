package com.example.webflux.repository;

import com.example.webflux.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CountryRepository extends JpaRepository<Country,Long> {

}
