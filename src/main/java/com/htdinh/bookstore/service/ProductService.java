package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface ProductService {

    Page<ProductResponse> getAllProduct(int pageNumber, int pageSize, Integer seed);

    Page<ProductResponse> getProductsByName(String searchKey, int pageNumber, int pageSize);

    void publishProductByShop(BigDecimal productId);

    Page<ProductResponse> getAllPublishForShop(int pageNumber, int pageSize);


    void draftProductByShop(BigDecimal productId);

    Page<ProductResponse> getAllDraftForShop(int pageNumber, int pageSize);
}
