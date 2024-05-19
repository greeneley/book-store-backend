package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.CartItemResponse;
import com.htdinh.bookstore.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponse toCartItemResponse(CartItem cartItem);
}
