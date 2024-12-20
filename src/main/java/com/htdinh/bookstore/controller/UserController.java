package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.ForgotPasswordRequest;
import com.htdinh.bookstore.dto.request.ResetPasswordRequest;
import com.htdinh.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam(value = "code") String code) {
        return ResponseEntity.ok().body(userService.verifyUser(code));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> processForgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        return ResponseEntity.ok().body(userService.processForgotPassword(request));

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> processResetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        return ResponseEntity.ok().body(userService.processResetPassword(request));

    }
}
