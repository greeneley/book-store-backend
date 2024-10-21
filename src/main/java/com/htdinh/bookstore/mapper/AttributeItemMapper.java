package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.request.AttributeItemRequest;
import com.htdinh.bookstore.dto.response.AttributeItemResponse;
import com.htdinh.bookstore.model.AttributeItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeItemMapper {
    AttributeItemResponse toAttributeItemResponse(AttributeItem attributeItem);

    AttributeItem toAttributeItem(AttributeItemRequest attributeItemRequest);
}
