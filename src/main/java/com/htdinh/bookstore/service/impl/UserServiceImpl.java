package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.UserRepository;
import com.htdinh.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String verifyUser(String code) {
        User user = userRepository.findByVerificationCode(code).orElseThrow(() -> new ResourceNotFoundException("Code not exist:::" + code));
        if (user.getIsActive()) {
            return "verified";
        } else {
            user.setIsActive(true);
            userRepository.save(user);
            return "success";
        }
    }
}
