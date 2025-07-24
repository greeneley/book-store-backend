package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htdinh.bookstore.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryResponse {
    @JsonProperty("cat_id")
    Long id;
    @NotNull
    @Size(max = 128)
    String name;

    @NotNull
    @Size(max = 128)
    private String slug;
    private Long parentCatId;

    private List<CategoryResponse> children = new ArrayList<>();

    private List<ProductResponse> books = new ArrayList<>();

}