package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.ProductCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCouponRepository extends JpaRepository<ProductCoupon, Long> {
}
