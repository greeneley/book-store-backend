package com.htdinh.bookstore.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class AttributeItemRequest {

    @NotNull
    @Size(max = 50)
    String name;
    @Size(max = 500)
    String description;
}
