package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull
    private Integer userId;
    @NotNull
    AddressRequest address;
    OrderStatus orderStatus;
    @Size(max = 50)
    String orderDate;
    @Size(max = 50)
    String paymentMethod;
    Long total;
    private Set<OrderItemRequest> orderItems = new LinkedHashSet<>();
}
