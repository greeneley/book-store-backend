package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.htdinh.bookstore.model.OrderItem}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest implements Serializable {
    private BookRequest book;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}