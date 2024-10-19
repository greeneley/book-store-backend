package com.htdinh.bookstore.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class RefreshTokenRequest {
    @NotNull
    private BigDecimal id;
}
