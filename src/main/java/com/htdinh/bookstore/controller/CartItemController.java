package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CartItemRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1//cart-item")
public class CartItemController {
    
    @GetMapping("/add")
    public ResponseEntity<String> addItem(@Valid @RequestBody CartItemRequest request) {
        return ResponseEntity.ok("Add succesffuly");
    }
}
