package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_CART", schema = "bookstore")
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_CART_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @Column(name = "CRT_ID", precision = 22)
    private BigDecimal crtId;

    @Size(max = 128)
    @Column(name = "CRT_DT", length = 128)
    private String crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private BigDecimal updtId;

    @Size(max = 128)
    @Column(name = "UPDT_DT", length = 128)
    private String updtDt;
    
}