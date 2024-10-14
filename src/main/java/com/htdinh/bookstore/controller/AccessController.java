package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.response.AuthResponse;
import com.htdinh.bookstore.jwt.JwtTokenUtil;
import com.htdinh.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AccessController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(user);
        AuthResponse response = new AuthResponse(user.getId(), request.getEmail(), accessToken);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> signup(@RequestBody @Valid AuthRequest request) {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody @Valid AuthRequest request) {
        return null;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> handleRefreshToken(@RequestBody @Valid AuthRequest request) {
        return null;
    }
}
