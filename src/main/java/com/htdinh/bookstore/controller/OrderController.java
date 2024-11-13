package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CheckoutRequest;
import com.htdinh.bookstore.dto.response.CheckoutResponse;
import com.htdinh.bookstore.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/create")
    public ResponseEntity<CheckoutResponse> checkoutReview(@RequestBody @Valid CheckoutRequest checkoutRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(checkoutService.checkoutReview(checkoutRequest));
    }

}

