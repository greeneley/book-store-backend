package com.htdinh.bookstore.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AttributeItemRequest {

    @NotNull
    @Size(max = 50)
    String name;
    @Size(max = 500)
    String description;
}
