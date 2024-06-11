package com.htdinh.bookstore.controller;


import com.htdinh.bookstore.dto.request.UserRequest;
import com.htdinh.bookstore.dto.response.UserResponse;
import com.htdinh.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> getUserById(@RequestBody @Valid UserRequest request) {
        UserResponse userResponse = userService.getUserWithToken(request.getId());
        return ResponseEntity.ok(userResponse);
    }

}
