package com.htdinh.bookstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResponse {
    Long id;
    @NotNull
    @Size(max = 128)
    String name;
}