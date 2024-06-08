package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.AddressRequest;
import com.htdinh.bookstore.dto.request.OrderItemRequest;
import com.htdinh.bookstore.dto.request.OrderRequest;
import com.htdinh.bookstore.dto.response.OrderResponse;
import com.htdinh.bookstore.model.Address;
import com.htdinh.bookstore.model.Order;
import com.htdinh.bookstore.model.OrderItem;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.AddressRepository;
import com.htdinh.bookstore.repository.OrderItemRepository;
import com.htdinh.bookstore.repository.OrderRepository;
import com.htdinh.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    @Transactional
    public String createOrder(OrderRequest orderRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AddressRequest addressInstance = orderRequest.getAddress();

        new Address();
        Address newAddress = addressRepository.save(Address.builder().addressId(addressInstance.getUserId()).province(addressInstance.getProvince()).district(addressInstance.getDistrict()).ward(addressInstance.getWard()).orderReceiverAddress(addressInstance.getOrderReceiverAddress()).receiverName(addressInstance.getReceiverName()).receiverPhone(addressInstance.getReceiverPhone()).createdAt(addressInstance.getCreatedAt()).build());

        System.out.println(newAddress.getAddressId());
//        Order newOrder = null;
//        
//        orderRepository.save(newOrder.builder().user(user).address())
        
        return null;
    }

    @Override
    public OrderResponse getOrderById(Integer id) {
        return null;
    }
}
