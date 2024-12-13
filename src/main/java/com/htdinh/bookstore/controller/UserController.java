package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam(value = "code") String code) {
        return ResponseEntity.ok().body(userService.verifyUser(code));
    }

}
