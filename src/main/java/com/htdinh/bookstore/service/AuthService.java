package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.request.LogoutRequest;
import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.dto.response.AuthResponse;

public interface AuthService {
    String register(RegisterRequest request);

    AuthResponse login(AuthRequest request);

    String logout(LogoutRequest request);
}
