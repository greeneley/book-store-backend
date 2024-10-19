package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.UserRepository;
import com.htdinh.bookstore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(RegisterRequest request) {
        validateUserRegistration(request);
        User user = createUserFromRequest(request);
        userRepository.save(user);
        return "User created successfully";
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
}
