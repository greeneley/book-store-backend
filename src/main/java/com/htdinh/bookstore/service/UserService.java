package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.UserResponse;

public interface UserService {
    UserResponse getUserWithToken(int id);
}
