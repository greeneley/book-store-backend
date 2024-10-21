package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.response.AttributeResponse;

import java.util.List;

public interface AttributeService {
    String createAttribute(String attName);

    List<AttributeResponse> getAllAttribute();
}
