package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "cartItemResponse", source = "cartItems")
    CartResponse toCartResponse(Cart cart);
}
