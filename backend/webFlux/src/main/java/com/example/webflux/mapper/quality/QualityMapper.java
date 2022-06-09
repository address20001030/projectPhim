package com.example.webflux.mapper.quality;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.Quality;
import com.example.webflux.model.response.quality.QualityResponse;


@org.mapstruct.Mapper(componentModel = "spring")
public interface QualityMapper extends Mapper<QualityResponse, Quality> {
}
