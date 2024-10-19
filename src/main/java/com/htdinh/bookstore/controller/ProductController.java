package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = {"", "/all"})
    public ResponseEntity<Page<ProductResponse>> getAllBooks(
            @RequestParam(value = "page")
            @Min(value = 0, message = "Page index must not be less than zero")
            @NotNull(message = "Page index must not be null")
            Integer pageNumber,

            @RequestParam(value = "size")
            @Min(value = 0, message = "Page size must not be less than 1")
            @NotNull(message = "Page size must not be null")
            Integer pageSize,
            @RequestParam(value = "seed")
            Integer seed
    ) {
        return ResponseEntity.ok(productService.getAllProduct(pageNumber, pageSize, seed));
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getBookById(@PathVariable(value = "id") int id) {
//        return ResponseEntity.ok(bookService.getBook(id));
//    }
//
//    @GetMapping("/search/{keySearch}")
//    public ResponseEntity<?> getListSearchBook(@PathVariable(value = "keySearch") String keySearch) {
//        // TODO;
//        return null;
////        return ResponseEntity.ok(bookService.getBook(id));
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> createProduct(String keySearch) {
//        // TODO;
//        return null;
//    }
//
//    @PatchMapping("/{product_id}")
//    public ResponseEntity<?> updateProduct(String keySearch) {
//        // TODO;
//        return null;
//    }
//
//    @PostMapping("/publish/{id}")
//    public ResponseEntity<?> publishProductByShop(String keySearch) {
//        // TODO;
//        return null;
//    }
//
//    @PostMapping("/unpublish/{id}")
//    public ResponseEntity<?> unPublishProductByShop(String keySearch) {
//        // TODO;
//        return null;
//    }
//
//    @GetMapping("/drafts/all")
//    public ResponseEntity<?> getAllDraftsForShop(String keySearch) {
//        // TODO;
//        return null;
//    }
//
//    @GetMapping("/published/all")
//    public ResponseEntity<?> getAllPublishForShop(String keySearch) {
//        // TODO;
//        return null;
//    }

}