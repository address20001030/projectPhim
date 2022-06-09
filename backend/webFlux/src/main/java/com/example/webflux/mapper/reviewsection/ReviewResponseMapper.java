package com.example.webflux.mapper.reviewsection;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.ReviewSection;
import com.example.webflux.model.response.reviewsection.ReviewResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ReviewResponseMapper extends Mapper<ReviewResponse, ReviewSection> {
}
