package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    @JsonProperty("cart_items")
    private List<CartItemResponse> cartItemResponse;
    private BigDecimal total;
}
