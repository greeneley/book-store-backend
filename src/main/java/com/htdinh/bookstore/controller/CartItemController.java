package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CartItemRequest;
import com.htdinh.bookstore.dto.response.CartItemResponse;
import com.htdinh.bookstore.service.CartItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/api/v1/cart-item")
public class CartItemController {
    
    @Autowired
    private CartItemService cartItemService;
    
    @GetMapping("/add")
    public ResponseEntity<CartItemResponse> addItem(@Valid @RequestBody CartItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addItem(request));
    }
    
    @GetMapping("/update")
    public ResponseEntity<String> updateItem(@Valid @RequestBody CartItemRequest request) {
        return ResponseEntity.ok("Update successfully");
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(
            @PathVariable("id")
            @NotNull(message = "Cart Item Id cannot be null")
            @Min(value = 1, message = "Cart Item Id value must be greater than 0")
            Integer id
    ) {
        cartItemService.deleteItem(id);
        return ResponseEntity.ok("Cart Item with id " + id + " was successfully deleted");
    }
}
