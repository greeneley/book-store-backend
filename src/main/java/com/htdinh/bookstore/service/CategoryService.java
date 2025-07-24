package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    String createCategory(String catName);

    List<CategoryResponse> getAllCategory();

    List<CategoryResponse> getAllCategoryHierarchy();
}
