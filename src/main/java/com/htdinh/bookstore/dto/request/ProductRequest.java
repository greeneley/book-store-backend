package com.htdinh.bookstore.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductRequest {

    @Size(max = 50)
    String name;
    String description;
    Long stock;
    @Size(max = 50)
    String regularPrice;
    @Size(max = 50)
    String salePrice;

//    List<Long> categoryIds;
}
