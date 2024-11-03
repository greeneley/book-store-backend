package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.ProductCartRequest;
import com.htdinh.bookstore.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart-items")
public class ProductCartController {

    @Autowired
    private ProductCartService productCartService;

    @PostMapping("")
    public ResponseEntity<String> addToCart(@RequestBody ProductCartRequest request) {
        return ResponseEntity.ok(productCartService.addToCart(request));
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateCart() {
        return null;
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteCartItem() {
        return null;
    }
}
