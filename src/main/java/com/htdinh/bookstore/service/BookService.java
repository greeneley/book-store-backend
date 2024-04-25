package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.BookResponse;

public interface BookService {
    BookResponse findBookById(Integer id);
    
}
