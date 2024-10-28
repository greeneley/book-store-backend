package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Page<Coupon> findAll(Pageable pageable);

    Optional<Coupon> findCouponByCode(String code);
}
