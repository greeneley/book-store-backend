package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.OrderResponse;
import com.htdinh.bookstore.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
}
