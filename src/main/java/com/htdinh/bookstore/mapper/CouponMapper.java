package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.CouponResponse;
import com.htdinh.bookstore.model.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponResponse toCouponResponse(Coupon coupon);
}
