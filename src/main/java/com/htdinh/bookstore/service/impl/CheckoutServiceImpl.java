package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.CheckoutRequest;
import com.htdinh.bookstore.dto.response.CheckoutResponse;
import com.htdinh.bookstore.dto.response.CheckoutResponse.CheckoutOrder;
import com.htdinh.bookstore.dto.response.CheckoutResponse.OrderProductNew;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public CheckoutResponse checkoutReview(CheckoutRequest checkoutRequest) {

        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(BigDecimal.ZERO);

        List<OrderProductNew> orderProductNewList = checkoutRequest.getOrderProductList().stream().map(orderProduct -> {
            Long productId = orderProduct.getProductId();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
            Long quantity = orderProduct.getQuantity();
            BigDecimal priceRaw = BigDecimal.valueOf(quantity).multiply(product.getRegularPrice());
            totalPrice.updateAndGet(preVal -> preVal.add(priceRaw));
            return new OrderProductNew(productId, quantity, priceRaw);
        }).collect(Collectors.toList());

        CheckoutOrder checkoutOrder = new CheckoutOrder(totalPrice.get());

        return new CheckoutResponse(checkoutRequest.getOrderProductList(), orderProductNewList, checkoutOrder);
    }
}
