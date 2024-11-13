package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.OrderCreationRequest;

public interface OrderService {
    String createOrder(OrderCreationRequest orderCreationRequest);
}
