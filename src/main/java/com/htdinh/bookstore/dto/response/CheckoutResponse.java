package com.htdinh.bookstore.dto.response;

import com.htdinh.bookstore.dto.request.CheckoutRequest;
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
    List<CheckoutRequest.OrderProduct> orderProductList;
    List<OrderProductNew> orderProductNews;
    CheckoutOrder checkoutOrder;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderProductNew implements Serializable {
        @NotNull
        Long productId;
        Long quantity;
        BigDecimal priceRaw;
//        BigDecimal priceApplyDiscount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CheckoutOrder implements Serializable {
        BigDecimal totalPrice;
    }
}
