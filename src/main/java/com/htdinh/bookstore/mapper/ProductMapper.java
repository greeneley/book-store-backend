package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
}

