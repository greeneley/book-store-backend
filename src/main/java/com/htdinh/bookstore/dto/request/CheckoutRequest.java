package com.htdinh.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class CheckoutRequest {
    @NotNull
    List<OrderProduct> orderProductList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderProduct implements Serializable {
        @NotNull
        Long productId;
        @NotNull
        Long quantity;
        @NotNull
        BigDecimal price;
        String couponCode;
    }
}
