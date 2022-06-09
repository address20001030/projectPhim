package com.example.webflux.controller;

import com.example.webflux.model.response.BaseResponse;
import com.example.webflux.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CategoryController extends BaseController{

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/category/getAll")
    public Mono<BaseResponse> getAllCategory(){
        return success(categoryService.getAllCategory());
    }

}
