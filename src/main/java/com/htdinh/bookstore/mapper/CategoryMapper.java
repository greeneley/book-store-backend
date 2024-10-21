package com.htdinh.bookstore.mapper;


import com.htdinh.bookstore.dto.response.CategoryResponse;
import com.htdinh.bookstore.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
}
