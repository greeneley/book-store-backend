package com.htdinh.bookstore.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private UserResponse userResponse;
    private List<CartItemResponse> cartItemResponses;
    private Integer total;
}
