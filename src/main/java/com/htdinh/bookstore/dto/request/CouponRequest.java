package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.enums.DiscountType;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CouponRequest {

    @NotNull
    @Size(max = 50)
    String code;
    List<Long> productIds;
    List<Long> excludeProductIds;
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
    private Boolean isActive = false;


}
