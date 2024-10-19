package com.htdinh.bookstore.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private String firstName;
    private String lastName;
    private String phone;
    private String birthDay;
}
