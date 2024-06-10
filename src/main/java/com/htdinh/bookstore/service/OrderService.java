package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.OrderRequest;
import com.htdinh.bookstore.dto.response.OrderResponse;

public interface OrderService {
    String createOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(Integer id);
}
