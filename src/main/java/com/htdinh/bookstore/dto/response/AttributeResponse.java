package com.htdinh.bookstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class AttributeResponse {
    Long id;
    @Size(max = 50)
    String name;
}
