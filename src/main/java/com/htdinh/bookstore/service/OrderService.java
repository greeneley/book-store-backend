package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.OrderCreationRequest;
import com.htdinh.bookstore.dto.request.OrderStatusRequest;
import com.htdinh.bookstore.dto.response.OrderDetailResponse;

public interface OrderService {
    String createOrder(OrderCreationRequest orderCreationRequest);

    String updateOrderStatus(Long orderId, OrderStatusRequest orderStatusRequest);

    OrderDetailResponse searchOrder(String orderNumber);
}
