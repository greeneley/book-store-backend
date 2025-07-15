package com.htdinh.bookstore.utils;

import com.htdinh.bookstore.exception.TokenExpiredException;
import com.htdinh.bookstore.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {
    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else if (principal instanceof String) {
            throw new TokenExpiredException("Access token was expired. You need to send the server the refresh token.");
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
        }
    }
}
