package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.OrderCreationRequest;
import com.htdinh.bookstore.dto.request.OrderStatusRequest;

public interface OrderService {
    String createOrder(OrderCreationRequest orderCreationRequest);

    String updateOrderStatus(Long orderId, OrderStatusRequest orderStatusRequest);
}
