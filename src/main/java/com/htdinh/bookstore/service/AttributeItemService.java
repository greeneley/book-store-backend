package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.AttributeItemRequest;
import com.htdinh.bookstore.dto.response.AttributeItemResponse;

import java.util.List;

public interface AttributeItemService {
//    String createAttribute(String attName);

    List<AttributeItemResponse> getAttributeItems(Long attributeId);

    String createAttributeItems(Long attributeId, List<AttributeItemRequest> attributeItemRequestList);
}
