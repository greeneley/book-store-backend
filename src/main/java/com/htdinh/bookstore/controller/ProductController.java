package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.response.BookResponse;
import com.htdinh.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/books")
public class ProductController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"", "/all"})
    public ResponseEntity<Page<BookResponse>> getAllBooks(
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
        return ResponseEntity.ok(bookService.getAllBooks(pageNumber, pageSize, seed));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping("/search/{keySearch}")
    public ResponseEntity<?> getListSearchBook(@PathVariable(value = "keySearch") String keySearch) {
        // TODO;
        return null;
//        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(String keySearch) {
        // TODO;
        return null;
    }

    @PatchMapping("/{product_id}")
    public ResponseEntity<?> updateProduct(String keySearch) {
        // TODO;
        return null;
    }

    @PostMapping("/publish/{id}")
    public ResponseEntity<?> publishProductByShop(String keySearch) {
        // TODO;
        return null;
    }

    @PostMapping("/unpublish/{id}")
    public ResponseEntity<?> unPublishProductByShop(String keySearch) {
        // TODO;
        return null;
    }

    @GetMapping("/drafts/all")
    public ResponseEntity<?> getAllDraftsForShop(String keySearch) {
        // TODO;
        return null;
    }

    @GetMapping("/published/all")
    public ResponseEntity<?> getAllPublishForShop(String keySearch) {
        // TODO;
        return null;
    }

}