package com.htdinh.bookstore.controller;


import com.htdinh.bookstore.dto.request.UserRequest;
import com.htdinh.bookstore.dto.response.UserResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.exception.TokenInValidException;
import com.htdinh.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("")
    public ResponseEntity<?> getUserById(@RequestBody @Valid UserRequest request) {
        try {
            UserResponse userResponse = userService.getUserWithToken(request.getId());
            return ResponseEntity.ok(userResponse);
        } catch (ResourceNotFoundException | TokenInValidException resourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());
        }
    }
    
}
