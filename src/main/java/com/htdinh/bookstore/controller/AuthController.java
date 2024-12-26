package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.request.LogoutRequest;
import com.htdinh.bookstore.dto.request.RefreshTokenRequest;
import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.dto.response.RefreshTokenResponse;
import com.htdinh.bookstore.jwt.JwtTokenUtil;
import com.htdinh.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseEntity.ok().body(authService.generateNewToken(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @GetMapping("/resend-email")
    public ResponseEntity<String> resend(@RequestParam(value = "email") @Valid String email) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.resend(email));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody @Valid LogoutRequest request) {

        return ResponseEntity.ok().body(authService.logout(request));
    }
}
