package com.example.webflux.service;

import com.example.webflux.model.entity.LinkMovie;
import com.example.webflux.model.entity.Quality;
import com.example.webflux.model.response.quality.QualityResponse;


import java.util.List;

public interface QualityService {
    List<QualityResponse> getAllQuality();

//    String getName(long id_link);
}
