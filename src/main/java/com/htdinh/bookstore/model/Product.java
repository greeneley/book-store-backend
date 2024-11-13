package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private Long id;

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
    private BigDecimal rating;

    @Column(name = "IS_PUBLISH")
    private Boolean isPublish;

    @Column(name = "REGULAR_PRICE", precision = 20, scale = 2)
    private BigDecimal regularPrice;

    @Column(name = "SALE_PRICE", precision = 20, scale = 2)
    private BigDecimal salePrice;

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

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductAttribute> productAttributes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductCategory> productCategories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> productImages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductVariant> productVariants = new LinkedHashSet<>();
}