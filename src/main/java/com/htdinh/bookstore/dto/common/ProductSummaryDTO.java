package com.htdinh.bookstore.dto.common;

import java.math.BigDecimal;

public record ProductSummaryDTO(Long id, String name, String description, BigDecimal regularPrice, BigDecimal salePrice, String thumbnailUrl) { }
