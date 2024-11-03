package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class ProductRequest {
    @Size(max = 50)
    String name;
    String description;
    Long stock;
    @Size(max = 50)
    BigDecimal regularPrice;
    @Size(max = 50)
    BigDecimal salePrice;

    List<Long> categoryIds;
    List<Long> attributeIds;
}
