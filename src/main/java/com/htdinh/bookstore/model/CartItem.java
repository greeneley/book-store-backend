package com.htdinh.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
@Entity
@Builder
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ITEM_ID", nullable = false)
    private Integer id;
    
    @ManyToOne()
    @JoinColumn(name = "CART_ID")
    private Cart cart;
    
    @ManyToOne()
    @JoinColumn(name = "BOOK_ID")
    private Book book;


    @Column(name = "QUANTITY")
    private Integer quantity;
    
    @Transient
    private BigDecimal subTotal;
}
