package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.dto.response.CheckoutResponse.OrderProductNew;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class OrderCreationRequest {
    @NotNull
    List<OrderProductNew> orderProducts;
    @NotNull
    BigDecimal totalPrice;
    String note;
    String payment;

    AddressOrderRequest addressOrder;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressOrderRequest implements Serializable {
        private String firstName;
        private String lastName;
        private String phone;
        private String province;
        private String district;
        private String ward;
    }
}
