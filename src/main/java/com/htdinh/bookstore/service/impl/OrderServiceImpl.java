package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.OrderCreationRequest;
import com.htdinh.bookstore.dto.request.OrderCreationRequest.AddressOrderRequest;
import com.htdinh.bookstore.dto.request.OrderStatusRequest;
import com.htdinh.bookstore.dto.response.OrderDetailResponse;
import com.htdinh.bookstore.dto.response.OrderDetailResponse.AddressOrderDTO;
import com.htdinh.bookstore.dto.response.OrderDetailResponse.OrderDetailProduct;
import com.htdinh.bookstore.enums.OrderStatus;
import com.htdinh.bookstore.enums.PaymentType;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.model.*;
import com.htdinh.bookstore.repository.AddressOrderRepository;
import com.htdinh.bookstore.repository.OrderDetailRepository;
import com.htdinh.bookstore.repository.OrderRepository;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private AddressOrderRepository addressOrderRepository;
    @Autowired
    private ProductRepository productRepository;

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
                .crtDt(getCurrentTimestamp())
                .updtDt(getCurrentTimestamp()).
                build();
        addressOrderRepository.save(addressOrder);

        User user = getCurrentUser();
        Orders order = Orders.builder().user(user).addressOrder(addressOrder)
                .orderNumber(RandomStringUtils.randomAlphanumeric(10))
                .orderStatus(OrderStatus.ORDERED)
                .note(request.getNote())
                .payment(PaymentType.BANK)
                .total(request.getTotalPrice())
                .crtDt(getCurrentTimestamp())
                .updtDt(getCurrentTimestamp()).build();

        orderRepository.save(order);
        request.getOrderProducts().forEach(orderProduct -> {
            Product product = productRepository.findById(orderProduct.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + orderProduct.getProductId()));
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order).product(product)
                    .price(product.getRegularPrice())
                    .total(orderProduct.getPriceRaw())
                    .quantity(orderProduct.getQuantity())
                    .crtDt(getCurrentTimestamp()).updtDt(getCurrentTimestamp()).build();
            orderDetailRepository.save(orderDetail);
        });
        return "Create order successfully";
    }

    @Override
    public String updateOrderStatus(Long orderId, OrderStatusRequest request) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        order.setOrderStatus(request.getStatus());

        orderRepository.save(order);
        return "Update order status successfully";
    }

    @Override
    public OrderDetailResponse searchOrder(String orderNumber) {
        Orders orders = orderRepository.findByOrderNumber(orderNumber).orElseThrow(() -> new ResourceNotFoundException("Order not found with order number: " + orderNumber));

        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orders.getId());

        List<OrderDetailProduct> orderProducts = new ArrayList<>();

        orderDetails.forEach(orderDetail -> {
            Product product = orderDetail.getProduct();
            OrderDetailProduct orderDetailProduct = OrderDetailProduct.builder().productId(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .quantity(orderDetail.getQuantity())
                    .priceRaw(orderDetail.getTotal())
                    .build();
            orderProducts.add(orderDetailProduct);
        });

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();

        orderDetailResponse.setOrderStatus(orders.getOrderStatus());
        orderDetailResponse.setNote(orders.getNote());
        orderDetailResponse.setPayment(orders.getPayment());
        orderDetailResponse.setTotalPrice(orders.getTotal());

        AddressOrder addressOrder = orders.getAddressOrder();

        orderDetailResponse.setAddressOrder(AddressOrderDTO.builder()
                .firstName(addressOrder.getFirstName())
                .lastName(addressOrder.getLastName())
                .phone(addressOrder.getPhone())
                .province(addressOrder.getProvince())
                .ward(addressOrder.getWard())
                .district(addressOrder.getDistrict())
                .build());

        orderDetailResponse.setOrderProduct(orderProducts);
        return orderDetailResponse;
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
