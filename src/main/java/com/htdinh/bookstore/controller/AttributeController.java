package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.AttributeItemRequest;
import com.htdinh.bookstore.dto.request.AttributeRequest;
import com.htdinh.bookstore.dto.response.AttributeItemResponse;
import com.htdinh.bookstore.dto.response.AttributeResponse;
import com.htdinh.bookstore.service.AttributeItemService;
import com.htdinh.bookstore.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeItemService attributeItemService;

    @PostMapping("/create")
    public ResponseEntity<String> createAttribute(@RequestBody @Valid AttributeRequest attributeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attributeService.createAttribute(attributeRequest.getName()));
    }

    @GetMapping("/all")
    public List<AttributeResponse> getAllAttribute() {
        return attributeService.getAllAttribute();
    }

    @GetMapping("/items/{attribute_id}")
    public List<AttributeItemResponse> getAttributeItems(@PathVariable("attribute_id") Long attributeId) {
        return attributeItemService.getAttributeItems(attributeId);
    }

    @PostMapping("/items/{attribute_id}/create")
    public ResponseEntity<String> createAttributeItem(@PathVariable(value = "attribute_id") Long attributeId, @RequestBody @Valid List<AttributeItemRequest> attributeItemRequestList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attributeItemService.createAttributeItems(attributeId, attributeItemRequestList));
    }
}
