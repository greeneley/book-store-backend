package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.BookResponse;
import org.springframework.data.domain.Page;

public interface BookService {
    
    Page<BookResponse> getAllBooks(int pageNumber, int pageSize, Integer seed);
    Page<BookResponse> getAllFavoriteBooks(int pageNumber, int pageSize, Integer seed);
    
    BookResponse getBook(Integer id);
}
