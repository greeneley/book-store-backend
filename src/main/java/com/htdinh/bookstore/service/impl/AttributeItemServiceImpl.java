package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.AttributeItemRequest;
import com.htdinh.bookstore.dto.response.AttributeItemResponse;
import com.htdinh.bookstore.mapper.AttributeItemMapper;
import com.htdinh.bookstore.model.Attribute;
import com.htdinh.bookstore.model.AttributeItem;
import com.htdinh.bookstore.repository.AttributeItemRepository;
import com.htdinh.bookstore.repository.AttributeRepository;
import com.htdinh.bookstore.service.AttributeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeItemServiceImpl implements AttributeItemService {

    private final AttributeItemMapper attributeItemMapper;
    private final AttributeItemRepository attributeItemRepository;
    private final AttributeRepository attributeRepository;

    @Autowired
    public AttributeItemServiceImpl(AttributeItemMapper attributeItemMapper, AttributeItemRepository attributeItemRepository, AttributeRepository attributeRepository) {
        this.attributeItemMapper = attributeItemMapper;
        this.attributeItemRepository = attributeItemRepository;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<AttributeItemResponse> getAttributeItems(Long attributeId) {
        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new IllegalArgumentException("Attribute not found with id: " + attributeId));
        return attributeItemRepository.findAllByAttribute(attribute).stream()
                .map(attributeItemMapper::toAttributeItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String createAttributeItems(Long attributeId, List<AttributeItemRequest> attributeItemRequestList) {
        validateAttributeItemRequestList(attributeItemRequestList);
        validateAttributeItemNames(attributeItemRequestList);

        Attribute attribute = attributeRepository.findById(attributeId)
                .orElseThrow(() -> new IllegalArgumentException("Attribute not found with id: " + attributeId));

        attributeItemRequestList.forEach(attributeItemRequest -> {
            AttributeItem attributeItem = attributeItemMapper.toAttributeItem(attributeItemRequest);
            attributeItem.setAttribute(attribute);
            attributeItem.setCrtDt(getCurrentTimestamp());
            attributeItem.setUpdtDt(getCurrentTimestamp());
            attributeItemRepository.save(attributeItem);
        });

        return "Create attribute items successfully";
    }

    private void validateAttributeItemRequestList(List<AttributeItemRequest> attributeItemRequestList) {
        if (attributeItemRequestList == null || attributeItemRequestList.isEmpty()) {
            throw new IllegalArgumentException("Attribute item request list cannot be null or empty");
        }
    }

    private void validateAttributeItemNames(List<AttributeItemRequest> attributeItemRequestList) {
        attributeItemRequestList.forEach(attributeItemRequest -> {
            if (attributeItemRepository.findByName(attributeItemRequest.getName()).isPresent()) {
                throw new RuntimeException("Name already exists");
            }
        });
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }
}
