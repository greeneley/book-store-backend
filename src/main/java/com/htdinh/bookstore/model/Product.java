package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT", schema = "bookstore")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false, precision = 22)
    private BigDecimal id;

    @Size(max = 50)
    @Column(name = "NAME", length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STOCK")
    private Long stock;

    @Column(name = "RATING")
    private Long rating;

    @Size(max = 50)
    @Column(name = "IS_PUBLISH", length = 50)
    private String isPublish;

    @Size(max = 50)
    @Column(name = "REGULAR_PRICE", length = 50)
    private String regularPrice;

    @Size(max = 50)
    @Column(name = "SALE_PRICE", length = 50)
    private String salePrice;

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

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductAttribute> productAttributes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductCart> productCarts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductCategory> productCategories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> productImages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> productVariants = new LinkedHashSet<>();


}