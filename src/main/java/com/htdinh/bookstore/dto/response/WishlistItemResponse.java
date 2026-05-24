package com.htdinh.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistItemResponse {

    private Long productId;
    private String name;
    private String author;
    private BigDecimal regularPrice;
    private BigDecimal salePrice;
    private String thumbnailUrl;
    private Long stock;
}

