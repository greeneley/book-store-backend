package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.CategoryResponse;
import com.htdinh.bookstore.mapper.CategoryMapper;
import com.htdinh.bookstore.model.Category;
import com.htdinh.bookstore.repository.CategoryRepository;
import com.htdinh.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public String createCategory(String catName) {
        if ((categoryRepository.findByName(catName)).isPresent()) {
            throw new RuntimeException("Category already exists");
        }

        Category category = new Category();
        category.setName(catName);
        category.setCrtDt(getCurrentTimestamp());
        category.setUpdtDt(getCurrentTimestamp());
        categoryRepository.save(category);

        return "Create category successfully";
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList());
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }
}
