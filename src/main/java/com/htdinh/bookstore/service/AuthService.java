package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.request.LogoutRequest;
import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.dto.response.AuthResponse;
import com.htdinh.bookstore.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AuthService {
    String register(RegisterRequest request) throws MessagingException, UnsupportedEncodingException;

    AuthResponse login(AuthRequest request);

    String logout(LogoutRequest request);

    void sendVerificationEmail(User user) throws MessagingException, UnsupportedEncodingException;
}
