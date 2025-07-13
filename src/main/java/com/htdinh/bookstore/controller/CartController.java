package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public ResponseEntity<CartResponse> getCartInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCartInfo());
    }

}
