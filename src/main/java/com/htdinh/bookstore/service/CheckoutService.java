package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.CheckoutRequest;
import com.htdinh.bookstore.dto.response.CheckoutResponse;

public interface CheckoutService {
    CheckoutResponse checkoutReview(CheckoutRequest checkoutRequest);
}
