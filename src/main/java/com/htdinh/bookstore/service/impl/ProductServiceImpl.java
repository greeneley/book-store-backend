package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.mapper.ProductMapper;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<ProductResponse> getAllProduct(int pageNumber, int pageSize, Integer seed) {
        return productRepository.findAllProduct(seed, PageRequest.of(pageNumber, pageSize)).map(productMapper::toProductResponse);
    }
}
