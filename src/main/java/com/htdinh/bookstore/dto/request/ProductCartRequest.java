package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class ProductCartRequest {


    BigDecimal productId;
    @NotNull
    Long quantity;
}
