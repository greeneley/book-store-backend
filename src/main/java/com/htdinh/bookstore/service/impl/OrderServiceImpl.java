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
import com.htdinh.bookstore.utils.AuthUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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

        User user = AuthUtils.getCurrentUser();
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
        Orders orders = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with order number: " + orderNumber));

        List<OrderDetailProduct> orderProducts = orderDetailRepository.findAllByOrderId(orders.getId()).stream()
                .map(orderDetail -> {
                    Product product = orderDetail.getProduct();
                    return OrderDetailProduct.builder()
                            .productId(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .quantity(orderDetail.getQuantity())
                            .priceRaw(orderDetail.getTotal())
                            .build();
                })
                .collect(Collectors.toList());

        AddressOrder addressOrder = orders.getAddressOrder();
        AddressOrderDTO addressOrderDTO = AddressOrderDTO.builder()
                .firstName(addressOrder.getFirstName())
                .lastName(addressOrder.getLastName())
                .phone(addressOrder.getPhone())
                .province(addressOrder.getProvince())
                .ward(addressOrder.getWard())
                .district(addressOrder.getDistrict())
                .build();

        return OrderDetailResponse.builder()
                .orderStatus(orders.getOrderStatus())
                .note(orders.getNote())
                .payment(orders.getPayment())
                .totalPrice(orders.getTotal())
                .addressOrder(addressOrderDTO)
                .orderProduct(orderProducts)
                .build();
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }
}
