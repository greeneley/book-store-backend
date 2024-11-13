package com.htdinh.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(max = 128)
    @Column(name = "CRT_DT", length = 128)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Size(max = 128)
    @Column(name = "UPDT_DT", length = 128)
    private LocalDateTime updtDt;


}