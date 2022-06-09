package com.example.webflux.service;

import com.example.webflux.model.response.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();
}
