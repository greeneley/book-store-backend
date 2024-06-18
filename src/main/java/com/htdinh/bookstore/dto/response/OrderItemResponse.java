package com.htdinh.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse implements Serializable {
    Integer orderItemId;
    Integer quantity;
    BigDecimal priceAtPurchase;
    BookResponse book;
}