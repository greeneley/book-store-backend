package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.CategoryResponse;
import com.htdinh.bookstore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class); 

    CategoryResponse toCategoryResponse(Category category);
}
