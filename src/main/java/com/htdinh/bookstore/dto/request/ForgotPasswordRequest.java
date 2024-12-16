package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ForgotPasswordRequest {

    @NotNull
    private String email;
}
