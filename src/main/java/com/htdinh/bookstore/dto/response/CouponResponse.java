package com.htdinh.bookstore.dto.response;

import com.htdinh.bookstore.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CouponResponse {
    Long id;
    @Size(max = 50)
    String code;
    @NotNull
    Boolean isActive;
    BigDecimal crtId;
    @Size(max = 128)
    String crtDt;
    BigDecimal updtId;
    @Size(max = 128)
    String updtDt;
    Set<ExcludeProductCouponDto> excludeProductCoupons;
    Set<ProductCouponDto> productCoupons;
    @Size(max = 500)
    private String description;
    private DiscountType discountType;
    private BigDecimal amount;
    private BigDecimal maxDiscountValue;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private BigDecimal minOrderValue;
    private Integer usageLimit;
    private Integer limitUsageToXItems;
    private Integer usageLimitPerUser;
    private Integer usesCount;
    private String usersUsed;

    /**
     * DTO for {@link com.htdinh.bookstore.model.ExcludeProductCoupon}
     */
    @Value
    public static class ExcludeProductCouponDto implements Serializable {
        @NotNull
        CouponResponse.ExcludeProductCouponDto.ProductDto product;

        /**
         * DTO for {@link com.htdinh.bookstore.model.Product}
         */
        @Value
        public static class ProductDto implements Serializable {
            BigDecimal id;
            @Size(max = 50)
            String name;
        }
    }

    /**
     * DTO for {@link com.htdinh.bookstore.model.ProductCoupon}
     */
    @Value
    public static class ProductCouponDto implements Serializable {
        ProductDto product;

        /**
         * DTO for {@link com.htdinh.bookstore.model.Product}
         */
        @Value
        public static class ProductDto implements Serializable {
            BigDecimal id;
            @Size(max = 50)
            String name;
        }
    }
}
