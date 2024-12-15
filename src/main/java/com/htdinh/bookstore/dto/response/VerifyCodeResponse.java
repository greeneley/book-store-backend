package com.htdinh.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCodeResponse {
    AuthResponse user;
    private Boolean success;
    private String message;
}
