package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.ProductCartRequest;
import com.htdinh.bookstore.dto.response.ProductCartResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.mapper.ProductCartMapper;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.model.ProductCart;
import com.htdinh.bookstore.model.ProductCartId;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.ProductCartRepository;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.service.ProductCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ProductCartServiceImpl implements ProductCartService {
    @Autowired
    private ProductCartRepository productCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCartMapper productCartMapper;
    @Override
    @Transactional
    public String addToCart(ProductCartRequest request) {
        User user = getCurrentUser();
        Long productId = request.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        ProductCart productCart = productCartRepository.findByUserAndProduct(user, product).orElse(null);

        if (productCart == null) {
            productCart = productCartRepository.save(ProductCart.builder()
                    .id(new ProductCartId(user.getId(), product.getId()))
                    .user(user)
                    .product(product)
                    .quantity(request.getQuantity())
                    .crtDt(getCurrentTimestamp())
                    .updtDt(getCurrentTimestamp())
                    .build()
            );
            productCart = productCartRepository.save(productCart);
        } else {
            productCart.setQuantity(productCart.getQuantity() + request.getQuantity());
        }
        productCart.setSubTotal(product.getRegularPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        log.info("Add item to cart successfully");

        return "Add item to cart successfully";
    }

    @Override
    @Transactional
    public String deleteCartItem(Long productId) {
        User user = getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        ProductCart productCart = productCartRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        productCartRepository.delete(productCart);
        return "delete successfully";
    }

    @Override
    public ProductCartResponse updateCartItem(ProductCartRequest request) {
        User user = getCurrentUser();
        Long productId = request.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        ProductCart productCart = productCartRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        productCart.setQuantity(request.getQuantity());
        productCart.setUpdtDt(getCurrentTimestamp());
        productCartRepository.save(productCart);
        return productCartMapper.toProductCartResponse(productCart);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }
}
