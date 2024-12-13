package com.htdinh.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_CART", schema = "bookstore")
public class ProductCart {

    @EmbeddedId
    private ProductCartId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, insertable = false, updatable = false)
    private Product product;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @Transient
    private BigDecimal subTotal;

    @Column(name = "CRT_ID", precision = 22)
    private Long crtId;

    @Column(name = "CRT_DT", length = 128)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Column(name = "UPDT_DT", length = 128)
    private LocalDateTime updtDt;


}