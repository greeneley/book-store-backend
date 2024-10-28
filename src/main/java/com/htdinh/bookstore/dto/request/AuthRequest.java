package com.htdinh.bookstore.dto.request;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Getter
public class AuthRequest {
    @NotNull
    private String username;

    @NotNull
    @Length(min = 5, max = 50)
    private String password;
}
