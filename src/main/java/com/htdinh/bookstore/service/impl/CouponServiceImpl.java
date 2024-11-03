package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.CouponRequest;
import com.htdinh.bookstore.dto.response.CouponResponse;
import com.htdinh.bookstore.mapper.CouponMapper;
import com.htdinh.bookstore.model.Coupon;
import com.htdinh.bookstore.model.ExcludeProductCoupon;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.model.ProductCoupon;
import com.htdinh.bookstore.repository.CouponRepository;
import com.htdinh.bookstore.repository.ExcludeProductCouponRepository;
import com.htdinh.bookstore.repository.ProductCouponRepository;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCouponRepository productCouponRepository;
    @Autowired
    private ExcludeProductCouponRepository excludeProductCouponRepository;

    @Override
    @Transactional
    public String createCouponCode(CouponRequest request) {

        validateCoupon(request);

        Coupon coupon = new Coupon();
        coupon.setCode(request.getCode());
        coupon.setDescription(request.getDescription());
        coupon.setDiscountType(request.getDiscountType());
        coupon.setAmount(request.getAmount());
        coupon.setMaxDiscountValue(request.getMaxDiscountValue());
        coupon.setStartDt(request.getStartDt());
        coupon.setEndDt(request.getEndDt());
        coupon.setMinOrderValue(request.getMinOrderValue());
        coupon.setUsageLimit(request.getUsageLimit());
        coupon.setLimitUsageToXItems(request.getLimitUsageToXItems());
        coupon.setUsageLimitPerUser(request.getUsageLimitPerUser());
        coupon.setIsActive(true);
        coupon.setCrtDt(getCurrentTimestamp());
        coupon.setUpdtDt(getCurrentTimestamp());
        couponRepository.save(coupon);

        List<Long> productIds = request.getProductIds();
        productIds.forEach(productId -> {
            Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product id not exists:::" + productId));
            ProductCoupon productCoupon = new ProductCoupon();

            productCoupon.setCoupon(coupon);
            productCoupon.setProduct(product);
            productCoupon.setCrtDt(getCurrentTimestamp());
            productCoupon.setUpdtDt(getCurrentTimestamp());

            productCouponRepository.save(productCoupon);
        });

        List<Long> excludeProductIds = request.getExcludeProductIds();
        excludeProductIds.forEach(excludeProductId -> {
            Product product = productRepository.findById(excludeProductId).orElseThrow(() -> new IllegalArgumentException("excludeProductId id not exists:::" + excludeProductId));
            ExcludeProductCoupon excludeProductCoupon = new ExcludeProductCoupon();

            excludeProductCoupon.setCoupon(coupon);
            excludeProductCoupon.setProduct(product);
            excludeProductCoupon.setCrtDt(getCurrentTimestamp());
            excludeProductCoupon.setUpdtDt(getCurrentTimestamp());

            excludeProductCouponRepository.save(excludeProductCoupon);
        });

        return "coupon created successfully";
    }

    private void validateCoupon(CouponRequest couponRequest) {
        if (couponRepository.findCouponByCode(couponRequest.getCode()).isPresent()) {
            throw new RuntimeException("Code already exists");
        }
    }

    @Override
    public Page<CouponResponse> getAllCouponCode(int pageNumber, int pageSize) {
        return couponRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(couponMapper::toCouponResponse);
    }

    @Override
    public String deleteCouponCode(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("couponId id not exists:::" + couponId));

        productCouponRepository.findAllByCoupon(coupon).ifPresent(productCoupons -> productCouponRepository.deleteAll(productCoupons));

        excludeProductCouponRepository.findAllByCoupon(coupon).ifPresent(excludeProductCoupons -> excludeProductCouponRepository.deleteAll(excludeProductCoupons));

        couponRepository.delete(coupon);
        return "delete successfully";
    }

    @Override
    public void updateCouponCode(CouponRequest request, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new IllegalArgumentException("coupon id not exists:::" + couponId));
        Optional.ofNullable(request.getDescription()).ifPresent(coupon::setDescription);
        Optional.ofNullable(request.getAmount()).ifPresent(coupon::setAmount);
        Optional.ofNullable(request.getMaxDiscountValue()).ifPresent(coupon::setMaxDiscountValue);
        Optional.ofNullable(request.getStartDt()).ifPresent(coupon::setStartDt);
        Optional.ofNullable(request.getEndDt()).ifPresent(coupon::setEndDt);
        Optional.ofNullable(request.getMinOrderValue()).ifPresent(coupon::setMinOrderValue);
        Optional.ofNullable(request.getUsageLimit()).ifPresent(coupon::setUsageLimit);
        Optional.ofNullable(request.getLimitUsageToXItems()).ifPresent(coupon::setLimitUsageToXItems);
        Optional.ofNullable(request.getUsageLimitPerUser()).ifPresent(coupon::setUsageLimitPerUser);
        Optional.ofNullable(request.getIsActive()).ifPresent(coupon::setIsActive);
        couponRepository.save(coupon);

        Optional.ofNullable(request.getProductIds()).ifPresent((productIds) -> {
            productCouponRepository.findAllByCoupon(coupon).ifPresent(productCoupons -> productCouponRepository.deleteAll(productCoupons));
            productIds.forEach(productId -> {
                Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product id not exists:::" + productId));
                ProductCoupon productCoupon = new ProductCoupon();

                productCoupon.setCoupon(coupon);
                productCoupon.setProduct(product);
                productCoupon.setCrtDt(getCurrentTimestamp());
                productCoupon.setUpdtDt(getCurrentTimestamp());

                productCouponRepository.save(productCoupon);
            });
        });

        Optional.ofNullable(request.getExcludeProductIds()).ifPresent((excludeProductIds) -> {
            excludeProductCouponRepository.findAllByCoupon(coupon).ifPresent(productCoupons -> excludeProductCouponRepository.deleteAll(productCoupons));
            excludeProductIds.forEach(productId -> {
                Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product id not exists:::" + productId));
                ExcludeProductCoupon excludeProductCoupon = new ExcludeProductCoupon();

                excludeProductCoupon.setCoupon(coupon);
                excludeProductCoupon.setProduct(product);
                excludeProductCoupon.setCrtDt(getCurrentTimestamp());
                excludeProductCoupon.setUpdtDt(getCurrentTimestamp());

                excludeProductCouponRepository.save(excludeProductCoupon);
            });
        });
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
