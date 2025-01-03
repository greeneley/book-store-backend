package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.AttributeResponse;
import com.htdinh.bookstore.exception.BusinessValidationException;
import com.htdinh.bookstore.mapper.AttributeMapper;
import com.htdinh.bookstore.model.Attribute;
import com.htdinh.bookstore.repository.AttributeRepository;
import com.htdinh.bookstore.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeMapper attributeMapper;
    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    @Transactional
    public String createAttribute(String attName) {
        if ((attributeRepository.findByName(attName)).isPresent()) {
            throw new BusinessValidationException("Attribute name already exists: " + attName);
        }

        Attribute attribute = new Attribute();
        attribute.setName(attName);
        attribute.setCrtDt(getCurrentTimestamp());
        attribute.setUpdtDt(getCurrentTimestamp());
        attributeRepository.save(attribute);

        return "Create attribute successfully";
    }

    @Override
    public List<AttributeResponse> getAllAttribute() {
        return attributeRepository.findAll().stream().map(attributeMapper::toAttributeResponse).collect(Collectors.toList());
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }
}
