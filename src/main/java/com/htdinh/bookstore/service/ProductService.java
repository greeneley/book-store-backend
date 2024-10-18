package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductResponse> getAllProduct(int pageNumber, int pageSize, Integer seed);
//    Page<BookResponse> getAllFavoriteBooks(int pageNumber, int pageSize, Integer seed);
//
//    BookResponse getBook(Integer id);
}
