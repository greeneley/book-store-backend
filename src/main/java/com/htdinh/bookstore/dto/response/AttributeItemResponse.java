package com.htdinh.bookstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AttributeItemResponse {

    Long id;
    @NotNull
    @Size(max = 50)
    String name;
    @Size(max = 500)
    String description;
}
