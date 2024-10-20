package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.mapper.ProductMapper;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Page<ProductResponse> getAllProduct(int pageNumber, int pageSize, Integer seed) {
        User user = getCurrentUser();
        return productRepository.findAllById(user.getId(), seed, PageRequest.of(pageNumber, pageSize)).map(productMapper::toProductResponse);
    }

    @Override
    public Page<ProductResponse> getProductsByName(String searchKey, int pageNumber, int pageSize) {
        return productRepository.findAllByName(searchKey, PageRequest.of(pageNumber, pageSize)).map(productMapper::toProductResponse);
    }

    @Override
    public void publishProductByShop(BigDecimal productId) {
        User user = getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        if (!product.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Product does not belong to the shop with id: " + user.getId());
        }

        product.setIsPublish("T");
        product.setIsDraft("F");
        productRepository.save(product);
    }

    @Override
    public void draftProductByShop(BigDecimal productId) {
        User user = getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        if (!product.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Product does not belong to the shop with id: " + user.getId());
        }

        product.setIsPublish("F");
        product.setIsDraft("T");
        productRepository.save(product);
    }
}
