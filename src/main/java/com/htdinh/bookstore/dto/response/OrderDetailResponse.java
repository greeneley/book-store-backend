package com.htdinh.bookstore.dto.response;

import com.htdinh.bookstore.enums.OrderStatus;
import com.htdinh.bookstore.enums.PaymentType;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    List<OrderDetailProduct> orderProduct;
    OrderStatus orderStatus;
    PaymentType payment;
    String note;
    BigDecimal totalPrice;
    AddressOrderDTO addressOrder;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderDetailProduct implements Serializable {
        Long productId;
        String name;
        String description;
        Long quantity;
        BigDecimal priceRaw;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddressOrderDTO implements Serializable {
        private String firstName;
        private String lastName;
        private String phone;
        private String province;
        private String district;
        private String ward;
    }

}
