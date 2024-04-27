package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.CategoryResponse;
import com.htdinh.bookstore.model.Category;

public class CategoryMapper {
    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
