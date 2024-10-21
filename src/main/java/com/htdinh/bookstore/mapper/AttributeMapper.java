package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.AttributeResponse;
import com.htdinh.bookstore.model.Attribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    AttributeResponse toAttributeResponse(Attribute attribute);
}
