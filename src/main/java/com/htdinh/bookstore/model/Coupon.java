package com.htdinh.bookstore.model;

import com.htdinh.bookstore.enums.DiscountType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
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

    @Size(max = 50)
    @Column(name = "DESCRIPTION", length = 50)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType;

    @Size(max = 50)
    @Column(name = "AMOUNT", length = 50)
    private String amount;

    @Size(max = 50)
    @Column(name = "MAX_DISCOUNT_VALUE", length = 50)
    private String maxDiscountValue;

    @Size(max = 50)
    @Column(name = "START_DT", length = 50)
    private String startDt;

    @Size(max = 50)
    @Column(name = "END_DT", length = 50)
    private String endDt;

    @Size(max = 50)
    @Column(name = "MIN_ORDER_VALUE", length = 50)
    private String minOrderValue;

    @Size(max = 50)
    @Column(name = "USAGE_LIMIT", length = 50)
    private String usageLimit;

    @Size(max = 50)
    @Column(name = "LIMIT_USAGE_TO_X_ITEMS", length = 50)
    private String limitUsageToXItems;

    @Size(max = 50)
    @Column(name = "USAGE_LIMIT_PER_USER", length = 50)
    private String usageLimitPerUser;

    @Size(max = 50)
    @Column(name = "USES_COUNT", length = 50)
    private String usesCount;

    @Size(max = 50)
    @Column(name = "USERS_USED", length = 50)
    private String usersUsed;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = false;

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

    @OneToMany(mappedBy = "coupon")
    private Set<ExcludeProductCoupon> excludeProductCoupons = new LinkedHashSet<>();

    @OneToMany(mappedBy = "coupon")
    private Set<ProductCoupon> productCoupons = new LinkedHashSet<>();


}

