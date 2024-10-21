package com.htdinh.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductCategoryResponse {

    @NotNull
    BigDecimal productCategoryId;

    @NotNull
    @Size(max = 50)
    String name;

}
