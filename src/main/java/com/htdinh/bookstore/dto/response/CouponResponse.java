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
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CouponResponse {
    Long id;
    @Size(max = 50)
    String code;
    @Size(max = 50)
    String description;
    DiscountType discountType;
    @Size(max = 50)
    String amount;
    @Size(max = 50)
    String maxDiscountValue;
    @Size(max = 50)
    String startDt;
    @Size(max = 50)
    String endDt;
    @Size(max = 50)
    String minOrderValue;
    @Size(max = 50)
    String usageLimit;
    @Size(max = 50)
    String limitUsageToXItems;
    @Size(max = 50)
    String usageLimitPerUser;
    @Size(max = 50)
    String usesCount;
    @Size(max = 50)
    String usersUsed;
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
