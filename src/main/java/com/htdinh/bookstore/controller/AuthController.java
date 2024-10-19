package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.request.RefreshTokenRequest;
import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.dto.response.AuthResponse;
import com.htdinh.bookstore.dto.response.RefreshTokenResponse;
import com.htdinh.bookstore.jwt.JwtTokenUtil;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.createJwtAccessToken(user);
        String refreshToken = jwtUtil.createJwtRefreshToken(String.valueOf(user.getId()));
        AuthResponse response = new AuthResponse(user.getId(), user.getUsername(), user.getEmail(), accessToken, refreshToken);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse(jwtUtil.createJwtRefreshToken(String.valueOf(request.getId())));
        return ResponseEntity.ok().body(refreshTokenResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody @Valid AuthRequest request) {
        return null;
    }
}
