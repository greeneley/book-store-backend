package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.ProductCartRequest;

public interface ProductCartService {
    String addToCart(ProductCartRequest request);

    String deleteCartItem(Long productId);
}
