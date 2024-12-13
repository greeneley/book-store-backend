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
@Table(name = "PRODUCT_VARIANT", schema = "bookstore")
public class ProductVariant {

    @EmbeddedId
    private ProductVariantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Size(max = 50)
    @NotNull
    @Column(name = "PRICE", nullable = false, length = 50)
    private String price;

    @Size(max = 50)
    @Column(name = "SKU", length = 50)
    private String sku;

    @Column(name = "STOCK")
    private Long stock;

    @Size(max = 50)
    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    @Column(name = "IS_PUBLISH")
    private Boolean isPublish;


    @Column(name = "CRT_ID", precision = 22)
    private BigDecimal crtId;

    @Column(name = "CRT_DT", length = 128)
    private String crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private BigDecimal updtId;

    @Column(name = "UPDT_DT", length = 128)
    private String updtDt;


}