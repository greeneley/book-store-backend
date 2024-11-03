package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.ProductCartResponse;
import com.htdinh.bookstore.model.ProductCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ProductCartMapper {
    ProductCartResponse toProductCartResponse(ProductCart productCart);
}
