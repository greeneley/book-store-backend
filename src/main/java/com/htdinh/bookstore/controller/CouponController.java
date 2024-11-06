package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CouponRequest;
import com.htdinh.bookstore.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/create")
    public ResponseEntity<String> createCouponCode(@RequestBody @Valid CouponRequest couponRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.createCouponCode(couponRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCouponCode(@RequestParam(value = "page")
                                              @Min(value = 0, message = "Page index must not be less than zero")
                                              @NotNull(message = "Page index must not be null")
                                              Integer pageNumber,
                                              @RequestParam(value = "size")
                                              @Min(value = 0, message = "Page size must not be less than 1")
                                              @NotNull(message = "Page size must not be null")
                                              Integer pageSize) {
        return ResponseEntity.ok(couponService.getAllCouponCode(pageNumber, pageSize));
    }

    @DeleteMapping("/delete/{coupon_id}")
    public String deleteDiscount(@PathVariable("coupon_id") Long couponId) {
        return couponService.deleteCouponCode(couponId);
    }

    @PatchMapping("/update/{id}")
    public void updateCouponCode(@RequestBody @Valid CouponRequest couponRequest, @PathVariable("id") Long id) {
        couponService.updateCouponCode(couponRequest, id);
    }
}