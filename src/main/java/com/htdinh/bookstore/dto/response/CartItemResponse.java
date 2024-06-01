package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htdinh.bookstore.model.Book;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {
    
    @JsonProperty("cart_item_id")
    private Integer id;
   
    private BookResponse book;
    private Integer quantity;
    private BigDecimal subTotal;
}
