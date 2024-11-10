package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.CheckoutRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutController {
    /*
     * BODY REQUEST
     * "orderList": [
     *  {
     *      "product_id": ,
     *      "quantity":20,
     *      "price": xxx,
     *      "discount": xxxx
     *},.....
     *
     *
     * }
     *
     * RESPONSE
     * return {
     *  "order": [...],
     *  "order_list_new": [
     *     {
     *          "productId":xxx,
     *          "discount": yyy,
     *          "priceRaw":xxxxx,
     *          "priceApplyDiscount":asjdklasjdsa,
     *          "quantity":100,
     *      }, ...
     * ],
     * "checkout_order": {
     *  "totalPrice":xxxx // Tong don tien hang
     *  "feeShip": 0,
     *  "totalDiscount": xxx // tong so tien duoc discount,
     *  "totalCheckout": askldas // tong so tien thanh toan tat ca don hang
     * }
     *
     * }
     *
     * */
    @PostMapping("/review")
    public ResponseEntity<String> checkoutReview(@RequestBody @Valid CheckoutRequest checkoutRequest) {
        return ResponseEntity.status(HttpStatus.OK).body("ABC");
    }

}

