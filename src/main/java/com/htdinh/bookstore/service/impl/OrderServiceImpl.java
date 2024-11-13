package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.OrderCreationRequest;
import com.htdinh.bookstore.dto.request.OrderCreationRequest.AddressOrderRequest;
import com.htdinh.bookstore.enums.OrderStatus;
import com.htdinh.bookstore.model.AddressOrder;
import com.htdinh.bookstore.model.Order;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.AddressOrderRepository;
import com.htdinh.bookstore.repository.OrderDetailRepository;
import com.htdinh.bookstore.repository.OrderRepository;
import com.htdinh.bookstore.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private AddressOrderRepository addressOrderRepository;

    @Override
    @Transactional
    public String createOrder(OrderCreationRequest request) {
        AddressOrderRequest addressOrderRequest = request.getAddressOrder();
        AddressOrder addressOrder = AddressOrder.builder().
                firstName(addressOrderRequest.getFirstName()).
                lastName(addressOrderRequest.getLastName()).
                phone(addressOrderRequest.getPhone()).
                province(addressOrderRequest.getProvince())
                .district(addressOrderRequest.getDistrict())
                .ward(addressOrderRequest.getWard())
                .crtDt(getCurrentTimestamp()).updtDt(getCurrentTimestamp()).
                build();


        User user = getCurrentUser();
        Order order = Order.builder().user(user).addressOrder(addressOrder)
                .orderNumber(RandomStringUtils.randomAlphanumeric(10))
                .orderStatus(String.valueOf(OrderStatus.ORDERED))
                .payment("BANK").crtDt(getCurrentTimestamp()).updtDt(getCurrentTimestamp()).build();

        return null;
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
