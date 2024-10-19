package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
}
