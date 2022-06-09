package com.example.webflux.mapper.reviewsection;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.ReviewSection;
import com.example.webflux.model.request.reviewsection.ReviewRequest;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ReviewSaveMapper extends Mapper<ReviewSection, ReviewRequest> {
}
