package com.htdinh.bookstore.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private UserResponse userResponse;
    private List<CartItemResponse> cartItemResponse;
    private BigDecimal total;
}
