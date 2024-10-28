package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class LogoutRequest {
    @NotNull
    private BigDecimal id;
}
