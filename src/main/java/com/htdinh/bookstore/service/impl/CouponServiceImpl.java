package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.CouponRequest;
import com.htdinh.bookstore.dto.response.CouponResponse;
import com.htdinh.bookstore.mapper.CouponMapper;
import com.htdinh.bookstore.repository.CouponRepository;
import com.htdinh.bookstore.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public String createCouponCode(CouponRequest request) {
//        Coupon coupon = new Coupon();
//        
//        coupon.setCode(request.getCode());
//        coupon.setDescription(request.getDescription());
//        coupon.set
        return "coupon created successfully";
    }

    @Override
    public Page<CouponResponse> getAllCouponCode(int pageNumber, int pageSize) {
        return couponRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(couponMapper::toCouponResponse);
    }
}
