package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.ProductSummaryDTO;
import com.htdinh.bookstore.dto.request.ProductRequest;
import com.htdinh.bookstore.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductResponse> getAllProduct(int pageNumber, int pageSize, Integer seed);
    ProductResponse getProduct(Long id);

    Page<ProductResponse> getProductsByName(String searchKey, int pageNumber, int pageSize);

    void publishProductByShop(Long productId);

    Page<ProductResponse> getAllPublishForShop(int pageNumber, int pageSize);


    void draftProductByShop(Long productId);

    Page<ProductResponse> getAllDraftForShop(int pageNumber, int pageSize);

    String createProduct(ProductRequest request);
    
    Page<ProductSummaryDTO> getProductsByCategory(Long catId, int pageNumber, int pageSize);
}
