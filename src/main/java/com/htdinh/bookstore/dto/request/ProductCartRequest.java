package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductCartRequest {
    @NotNull
    Long productId;
    @NotNull
    Long quantity;
}
