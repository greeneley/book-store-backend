package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.request.LogoutRequest;
import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.dto.response.AuthResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.jwt.JwtTokenUtil;
import com.htdinh.bookstore.model.RefreshToken;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.RefreshTokenRepository;
import com.htdinh.bookstore.repository.UserRepository;
import com.htdinh.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(RegisterRequest request) {
        validateUserRegistration(request);
        User user = createUserFromRequest(request);
        userRepository.save(user);
        return "User created successfully";
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.createJwtAccessToken(user);
        String generatedRefreshToken = jwtUtil.createJwtRefreshToken(String.valueOf(user.getId()));

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setValue(generatedRefreshToken);
        refreshToken.setExpireDt(convertDateToString(jwtUtil.extractExpiration(generatedRefreshToken)));
        refreshToken.setCrtDt(getCurrentTimestamp());
        refreshTokenRepository.save(refreshToken);

        return new AuthResponse(user.getId(), user.getUsername(), user.getEmail(), accessToken, generatedRefreshToken);
    }

    public String logout(LogoutRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new ResourceNotFoundException("User with ID = " + request.getId() + " not found"));
        refreshTokenRepository.deleteByUserId(user.getId());
        return "logout successfully";
    }

    private void validateUserRegistration(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
    }

    private User createUserFromRequest(RegisterRequest request) {
        User user = new User();
        user.setId(generateNewUserId());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive("A");
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUserType("B");
        user.setBirthDay(request.getBirthDay());
        user.setCrtDt(getCurrentTimestamp());
        return user;
    }

    private BigDecimal generateNewUserId() {
        BigDecimal maxId = userRepository.getMaxId();
        return (maxId == null) ? BigDecimal.ONE : maxId.add(BigDecimal.ONE);
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    private String convertDateToString(Date input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(input);
    }
}
