package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.request.LogoutRequest;
import com.htdinh.bookstore.dto.request.RefreshTokenRequest;
import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.dto.response.AuthResponse;
import com.htdinh.bookstore.dto.response.RefreshTokenResponse;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AuthService {
    String register(RegisterRequest request) throws MessagingException, UnsupportedEncodingException;

    String resend(String email) throws MessagingException, UnsupportedEncodingException;

    AuthResponse login(AuthRequest request);

    String logout(LogoutRequest request);

    RefreshTokenResponse generateNewToken(RefreshTokenRequest request);

}
