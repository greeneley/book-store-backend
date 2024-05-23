package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.CartItemResponse;
import com.htdinh.bookstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "cartResponse", source = "user")
    CartItemResponse toCartItemResponse(CartItem cartItem);
}
