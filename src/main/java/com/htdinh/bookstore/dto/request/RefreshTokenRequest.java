package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RefreshTokenRequest {
    @NotNull
    private Long id;
    @NotNull
    private String refreshToken;
}
