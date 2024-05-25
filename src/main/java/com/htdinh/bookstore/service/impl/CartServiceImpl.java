package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.CartItemResponse;
import com.htdinh.bookstore.dto.response.CartResponse;
import com.htdinh.bookstore.dto.response.UserResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.mapper.CartItemMapper;
import com.htdinh.bookstore.mapper.CartMapper;
import com.htdinh.bookstore.mapper.UserMapper;
import com.htdinh.bookstore.model.Cart;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.CartItemRepository;
import com.htdinh.bookstore.repository.CartRepository;
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
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private CartMapper cartMapper;
    
    @Override
    public CartResponse getAllInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        
        List<CartItemResponse> res = new ArrayList<>();
        
        Cart cart = cartRepository.findCartByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User id " + user.getId() + " not found"));
        
        cartItemRepository.findAllByCartId(cart.getCartId()).forEach(item -> {
            BigDecimal price = item.getBook().getPrice();
            BigDecimal quantity = new BigDecimal(item.getQuantity());
            item.setSubTotal(price.multiply(quantity));
            
            res.add(cartItemMapper.toCartItemResponse(item));
        });
        
        BigDecimal total = new BigDecimal(0);
        
        for (CartItemResponse item: res) {
            total = total.add(item.getSubTotal());
        }
       
        cart.setTotal(total);
        
        return cartMapper.toCartResponse(cart);
       
    }
}
