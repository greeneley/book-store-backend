package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.enums.OrderStatus;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderStatusRequest {
    @NotNull
    private OrderStatus status;
}
