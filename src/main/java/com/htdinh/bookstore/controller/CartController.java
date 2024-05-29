package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping(value = "")
    public ResponseEntity<CartResponse> getAllInfo() {
        CartResponse cartResponse = cartService.getAllInfo();
        return ResponseEntity.ok(cartResponse);
    }
}