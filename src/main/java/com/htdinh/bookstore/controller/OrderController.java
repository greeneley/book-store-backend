package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.OrderCreationRequest;
import com.htdinh.bookstore.dto.request.OrderStatusRequest;
import com.htdinh.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderCreationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(request));
    }

    @PatchMapping("/update-status/{order_id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable("order_id") Long id, @RequestBody @Valid OrderStatusRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderStatus(id, request));
    }
}

