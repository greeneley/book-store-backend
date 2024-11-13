package com.htdinh.bookstore.model;

import com.htdinh.bookstore.enums.DiscountType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "COUPON", schema = "bookstore")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_ID", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "CODE", length = 50)
    private String code;

    @Size(max = 500)
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType;

    @Column(name = "AMOUNT", precision = 22)
    private BigDecimal amount;

    @Column(name = "MAX_DISCOUNT_VALUE", precision = 22)
    private BigDecimal maxDiscountValue;

    @Column(name = "START_DT", precision = 22)
    private LocalDateTime startDt;

    @Column(name = "END_DT", precision = 22)
    private LocalDateTime endDt;

    @Column(name = "MIN_ORDER_VALUE", precision = 22)
    private BigDecimal minOrderValue;

    @Column(name = "USAGE_LIMIT", precision = 22)
    private Integer usageLimit;

    @Column(name = "LIMIT_USAGE_TO_X_ITEMS", precision = 22)
    private Integer limitUsageToXItems;

    @Column(name = "USAGE_LIMIT_PER_USER", precision = 22)
    private Integer usageLimitPerUser;

    @Column(name = "USES_COUNT", precision = 22)
    private Integer usesCount;

    @Column(name = "USERS_USED", precision = 22)
    private String usersUsed;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = false;

    @Column(name = "CRT_ID", precision = 22)
    private Long crtId;

    @Column(name = "CRT_DT", precision = 22)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Column(name = "UPDT_DT", precision = 22)
    private LocalDateTime updtDt;

    @OneToMany(mappedBy = "coupon")
    private Set<ExcludeProductCoupon> excludeProductCoupons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "coupon")
    private Set<ProductCoupon> productCoupons = new LinkedHashSet<>();


}

