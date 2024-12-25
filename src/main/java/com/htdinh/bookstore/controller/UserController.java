package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.ForgotPasswordRequest;
import com.htdinh.bookstore.dto.request.ProfileUpdateRequest;
import com.htdinh.bookstore.dto.request.ResetPasswordRequest;
import com.htdinh.bookstore.dto.response.ProfileUserResponse;
import com.htdinh.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/profile")
    public ResponseEntity<ProfileUserResponse> getProfileUser() {
        return ResponseEntity.ok().body(userService.getProfileUser());
    }

    @PostMapping("/avatar/upload")
    public ResponseEntity<String> uploadAvatarProfile(@RequestPart(value = "file") MultipartFile multipartFile) throws Exception {
        return ResponseEntity.ok().body(userService.uploadAvatarProfile(multipartFile));
    }

    @PostMapping("/profile/update")
    public ResponseEntity<String> updateProfileUser(@RequestBody ProfileUpdateRequest request) {
        return ResponseEntity.ok().body(userService.updateProfileUser(request));
    }
}
