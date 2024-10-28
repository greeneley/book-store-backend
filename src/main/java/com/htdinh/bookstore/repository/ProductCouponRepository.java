package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Coupon;
import com.htdinh.bookstore.model.ProductCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCouponRepository extends JpaRepository<ProductCoupon, Long> {
    Optional<List<ProductCoupon>> findAllByCoupon(Coupon coupon);
}
