package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.UserResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.exception.TokenInValidException;
import com.htdinh.bookstore.mapper.UserMapper;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.UserRepository;
import com.htdinh.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserResponse getUserWithToken(int id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        int extractedId = user.getId();

        if (extractedId == id) {
            return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID = " + id + " not found")));
        }
        throw new TokenInValidException("Token is invalid");
    }
}
