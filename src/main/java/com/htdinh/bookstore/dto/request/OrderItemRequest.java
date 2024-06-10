package com.htdinh.bookstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest implements Serializable {
    private Integer bookId;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}