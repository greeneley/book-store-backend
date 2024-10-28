package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.ExcludeProductCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcludeProductCouponRepository extends JpaRepository<ExcludeProductCoupon, Long> {
}
