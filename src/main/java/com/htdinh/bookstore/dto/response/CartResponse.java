package com.htdinh.bookstore.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private UserResponse userResponse;
    private List<CartItemResponse> cartItemResponses;
    private BigDecimal total;
}
