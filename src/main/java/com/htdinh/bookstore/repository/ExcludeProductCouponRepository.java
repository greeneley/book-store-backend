package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Coupon;
import com.htdinh.bookstore.model.ExcludeProductCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExcludeProductCouponRepository extends JpaRepository<ExcludeProductCoupon, Long> {
    Optional<List<ExcludeProductCoupon>> findAllByCoupon(Coupon coupon);
}
