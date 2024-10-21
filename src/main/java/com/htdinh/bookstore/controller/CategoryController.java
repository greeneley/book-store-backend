package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CategoryRequest;
import com.htdinh.bookstore.dto.response.CategoryResponse;
import com.htdinh.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryRequest.getCatName()));
    }

    @GetMapping("/all")
    public List<CategoryResponse> getAllCategory() {
        return categoryService.getAllCategory();
    }
}
