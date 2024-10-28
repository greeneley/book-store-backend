package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.enums.DiscountType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CouponRequest {

    @NotNull
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
}
