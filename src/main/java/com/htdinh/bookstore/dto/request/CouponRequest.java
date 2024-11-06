package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.enums.DiscountType;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class CouponRequest {

    @NotNull
    @Size(max = 50)
    String code;
    @Size(max = 50)
    String description;
    @NotNull
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
    List<Long> productIds;
    List<Long> excludeProductIds;
    private Boolean isActive = false;
}
