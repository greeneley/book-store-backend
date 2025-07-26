package com.htdinh.bookstore.dto;

import com.htdinh.bookstore.model.ProductImage;

import java.math.BigDecimal;
import java.util.Set;

public record ProductSummaryDTO(Long id,
                                String name,
                                String description,
                                BigDecimal regularPrice,
                                BigDecimal salePrice,
                                String url
                                ) {

}
