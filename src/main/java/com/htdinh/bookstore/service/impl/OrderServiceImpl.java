package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.AddressRequest;
import com.htdinh.bookstore.dto.request.OrderItemRequest;
import com.htdinh.bookstore.dto.request.OrderRequest;
import com.htdinh.bookstore.dto.response.OrderResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.model.*;
import com.htdinh.bookstore.repository.AddressRepository;
import com.htdinh.bookstore.repository.BookRepository;
import com.htdinh.bookstore.repository.OrderItemRepository;
import com.htdinh.bookstore.repository.OrderRepository;
import com.htdinh.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public String createOrder(OrderRequest orderRequest) {
        
        /*  1. Create address
            2. Create order
            3. Create order item
         */

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AddressRequest addressRequest = orderRequest.getAddress();

        Address newAddressInfo = addressRepository.save(Address.builder()
                .user(user)
                .province(addressRequest.getProvince())
                .district(addressRequest.getDistrict())
                .ward(addressRequest.getWard())
                .orderReceiverAddress(addressRequest.getOrderReceiverAddress())
                .receiverName(addressRequest.getReceiverName())
                .receiverPhone(addressRequest.getReceiverPhone())
                .createdAt(addressRequest.getCreatedAt())
                .build());

        Order newOrderInfo = orderRepository.save(Order.builder()
                .user(user)
                .address(newAddressInfo)
                .orderStatus(orderRequest.getOrderStatus())
                .orderDate(orderRequest.getOrderDate())
                .paymentMethod(orderRequest.getPaymentMethod())
                .total(orderRequest.getTotal())
                .build());

        Set<OrderItem> orderItems = new LinkedHashSet<>();

        for (OrderItemRequest newOrderItemRequest : orderRequest.getOrderItems()) {
            Book newBookItem = bookRepository.findById(newOrderItemRequest.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book with ID = " + newOrderItemRequest.getBookId() + " not found"));

            OrderItem newOrderItem = OrderItem.builder().order(newOrderInfo).book(newBookItem).quantity(newOrderItemRequest.getQuantity()).priceAtPurchase(newOrderItemRequest.getPriceAtPurchase()).build();
            orderItems.add(newOrderItem);
        }

        orderItemRepository.saveAll(orderItems);

        return "Create order successfully";
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        return null;
    }
}
