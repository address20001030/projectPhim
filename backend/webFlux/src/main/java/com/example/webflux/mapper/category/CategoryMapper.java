package com.example.webflux.mapper.category;

import com.example.webflux.mapper.Mapper;
import com.example.webflux.model.entity.Category;
import com.example.webflux.model.response.category.CategoryResponse;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CategoryMapper extends Mapper<CategoryResponse, Category> {
}
