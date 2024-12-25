package com.htdinh.bookstore.dto.request;

import lombok.Getter;

@Getter
public class ProfileUpdateRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String birthday;
    private String newPassword;
//    private String email;
}
