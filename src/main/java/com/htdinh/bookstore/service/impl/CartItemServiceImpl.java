package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.CartItemRequest;
import com.htdinh.bookstore.dto.response.CartItemResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.mapper.CartItemMapper;
import com.htdinh.bookstore.model.Book;
import com.htdinh.bookstore.model.Cart;
import com.htdinh.bookstore.model.CartItem;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.BookRepository;
import com.htdinh.bookstore.repository.CartItemRepository;
import com.htdinh.bookstore.repository.CartRepository;
import com.htdinh.bookstore.service.CartItemService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemMapper cartItemMapper;
    @Override
    @Transactional
    public CartItemResponse addItem(CartItemRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = cartRepository.findCartByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User id " + user.getId() + " not found"));

        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book with ID = " + request.getBookId() + " not found"));
        
        CartItem cartItem = cartItemRepository.findByCartAndBook(cart, book).orElse(null);
        
        if (cartItem == null) {
            cartItem = cartItemRepository.save(CartItem.builder().cart(cart).book(book).quantity(request.getQuantity()).build());
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        }

        BigDecimal price = book.getPrice();
        cartItem.setSubTotal(price.multiply(new BigDecimal(cartItem.getQuantity())));
        log.info("Add item to cart successfully");

        return cartItemMapper.toCartItemResponse(cartItem);
    }

    @Override
    public void deleteItem(int cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        if (cartItem != null) {
            cartItemRepository.deleteById(cartItemId);
            log.info("Cart Item with ID = " + cartItemId + " deleted successfully");
        } else {
            throw new ResourceNotFoundException("Cart item with id " + cartItemId + " not found");
        }
        
    }

    @Override
    public CartItemResponse updateItem(CartItemRequest request) {
        return null;
    }
}
