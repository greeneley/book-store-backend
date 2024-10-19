package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    @JsonProperty("_id")
    private BigDecimal id;
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
}