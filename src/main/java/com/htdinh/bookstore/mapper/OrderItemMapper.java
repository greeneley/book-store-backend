package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.OrderItemResponse;
import com.htdinh.bookstore.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
