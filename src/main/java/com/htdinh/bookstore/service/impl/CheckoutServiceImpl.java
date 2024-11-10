package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.CheckoutRequest;
import com.htdinh.bookstore.dto.request.CheckoutRequest.*;
import com.htdinh.bookstore.dto.response.CheckoutResponse;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;


@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public CheckoutResponse checkoutReview(CheckoutRequest checkoutRequest) {
        
        List<OrderProductNew> orderProductNewList = checkoutRequest.getOrderProductList().stream().map(orderProduct -> {
            Long productId = orderProduct.getProductId();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
            
            String coupon = orderProduct.getCouponCode();
            BigDecimal priceRaw = product.getRegularPrice();
        }).collect(Collectors.toList());
        
        
        return new CheckoutResponse(checkoutRequest.getOrderProductList(), ,)

    }
}
