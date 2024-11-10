package com.htdinh.bookstore.dto.response;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CheckoutResponse {
    @NotNull
    List<OrderProduct> orderProductList;
    List<OrderProductNew> orderProductNews;
    List<CheckoutOrder> checkoutOrder;

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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderProductNew implements Serializable {
        @NotNull
        Long productId;
        String couponCode;
        Long quantity;
        BigDecimal priceRaw;
        BigDecimal priceApplyDiscount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CheckoutOrder implements Serializable {
        Long totalPrice;
        Long feeShip;
        Long totalDiscount;
        Long totalCheckout;
    }
}
