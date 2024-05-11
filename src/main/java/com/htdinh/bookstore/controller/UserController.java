package com.htdinh.bookstore.controller;


import com.htdinh.bookstore.dto.UserRequest;
import com.htdinh.bookstore.dto.UserResponse;
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
    
    
    @GetMapping("")
    public ResponseEntity<?> getUserById(
            @RequestParam(value="id")
            int id,
            @RequestParam(value="token")
            String token
    ) {
        try {
            UserResponse userResponse = userService.getUserWithToken(id, token);
            return ResponseEntity.ok(userResponse);
        } catch (ResourceNotFoundException | TokenInValidException resourceNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());
        }
    }
    
}
