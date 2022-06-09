package com.example.webflux.service.Impl;

import com.example.webflux.mapper.category.CategoryMapper;
import com.example.webflux.model.entity.Category;
import com.example.webflux.model.response.category.CategoryResponse;
import com.example.webflux.repository.CategoryRepository;
import com.example.webflux.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        return categoryMapper.to(categories);
    }
}
