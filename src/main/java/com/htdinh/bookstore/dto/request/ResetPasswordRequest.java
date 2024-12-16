package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ResetPasswordRequest {

    @NotNull
    private String token;

    @NotNull
    private String password;
}
