package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartResponse toCartResponse(Cart cart);
}
