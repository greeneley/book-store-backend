package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductCartRequest {
    Long productId;
    @NotNull
    Long quantity;
}
