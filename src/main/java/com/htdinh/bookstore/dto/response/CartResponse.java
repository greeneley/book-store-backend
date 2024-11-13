package com.htdinh.bookstore.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private List<ProductCartResponse> items;
    private BigDecimal total;
}
