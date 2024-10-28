package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.CouponRequest;
import com.htdinh.bookstore.dto.response.CouponResponse;
import org.springframework.data.domain.Page;

public interface CouponService {
    String createCouponCode(CouponRequest request);

    Page<CouponResponse> getAllCouponCode(int pageNumber, int pageSize);

    String deleteCouponCode(Long couponId);
}
