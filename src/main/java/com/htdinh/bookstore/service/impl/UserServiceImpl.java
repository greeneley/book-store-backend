package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.UserResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.exception.TokenInValidException;
import com.htdinh.bookstore.jwt.JwtTokenUtil;
import com.htdinh.bookstore.mapper.UserMapper;
import com.htdinh.bookstore.repository.UserRepository;
import com.htdinh.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Override
    public UserResponse getUserWithToken(int id, String token) {
        int extractedId = Integer.parseInt(jwtTokenUtil.getId(token));
        
        if (extractedId == id) {
            return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID = " + id + " not found")));
        }

        throw new TokenInValidException("Token is invalid");
    }
}
