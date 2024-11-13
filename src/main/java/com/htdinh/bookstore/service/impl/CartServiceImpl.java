package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.dto.response.ProductCartResponse;
import com.htdinh.bookstore.mapper.ProductCartMapper;
import com.htdinh.bookstore.model.ProductCart;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.ProductCartRepository;
import com.htdinh.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductCartRepository productCartRepository;

    @Autowired
    private ProductCartMapper productCartMapper;

    @Override
    public CartResponse getCartInfo() {
        User user = getCurrentUser();

        List<ProductCart> productCartsList = new ArrayList<>();
        productCartRepository.findAllByUser(user).forEach(item -> {
            item.setSubTotal(item.getProduct().getRegularPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            productCartsList.add(item);
        });

        List<ProductCartResponse> productCartResponses = new ArrayList<>();

        AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);
        productCartsList.forEach(productCart -> {
            total.updateAndGet(preValue -> preValue.add(productCart.getSubTotal()));
            productCartResponses.add(productCartMapper.toProductCartResponse(productCart));
        });
        
        return CartResponse.builder().items(productCartResponses).total(total.get()).build();
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
