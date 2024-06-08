package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.OrderRequest;
import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.dto.response.OrderResponse;
import com.htdinh.bookstore.service.CartService;
import com.htdinh.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @PostMapping(value = { "", "/" })
    private ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }
    
    @GetMapping(value = {"/search/{order_id}"})
    private ResponseEntity<OrderResponse> searchOrder(@PathVariable(value= "order_id") Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}