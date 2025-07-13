package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.ProductCartRequest;
import com.htdinh.bookstore.dto.response.ProductCartResponse;

public interface ProductCartService {
    String addToCart(ProductCartRequest request);

    String deleteCartItem(Long productId);

    ProductCartResponse updateCartItem(ProductCartRequest request);

}
