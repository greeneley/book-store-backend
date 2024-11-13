package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    @JsonProperty("_id")
    private Long id;
    private String username;
    private String email;
    private String accessToken;
    private String refreshToken;
}