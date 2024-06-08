package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htdinh.bookstore.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    @JsonProperty("order_id")
    Integer orderId;
    @NotNull
    UserResponse user;
    @NotNull(message = "Address not null")
    AddressResponse address;
    OrderStatus orderStatus;
    @Size(max = 50)
    String orderDate;
    @Size(max = 50)
    String paymentMethod;
    Long total;
    Set<OrderItemResponse> orderItems;
}
