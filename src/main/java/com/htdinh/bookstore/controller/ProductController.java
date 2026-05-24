package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.ProductRequest;
import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.htdinh.bookstore.dto.common.ProductSummaryDTO;

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

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") @NotNull(message = "Product ID cannot be null")
                                                      @Min(value = 1, message = "Product ID must be greater than 0") Long productId) {
        return ResponseEntity.ok().body(productService.getProduct(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsByName(@RequestParam(value = "key") String keySearch,
                                               @RequestParam(value = "page")
                                               @Min(value = 0, message = "Page index must not be less than zero")
                                               @NotNull(message = "Page index must not be null")
                                               Integer pageNumber,
                                               @RequestParam(value = "size")
                                               @Min(value = 0, message = "Page size must not be less than 1")
                                               @NotNull(message = "Page size must not be null")
                                               Integer pageSize) {
        return ResponseEntity.ok(productService.getProductsByName(keySearch, pageNumber, pageSize));
    }


    @PostMapping("/publish/{product_id}")
    public void publishProductByShop(@PathVariable(value = "product_id") Long productId) {
        productService.publishProductByShop(productId);
    }

    @GetMapping("/publish/all")
    public ResponseEntity<?> getAllPublishForShop(@RequestParam(value = "page")
                                                  @Min(value = 0, message = "Page index must not be less than zero")
                                                  @NotNull(message = "Page index must not be null")
                                                  Integer pageNumber,
                                                  @RequestParam(value = "size")
                                                  @Min(value = 0, message = "Page size must not be less than 1")
                                                  @NotNull(message = "Page size must not be null")
                                                  Integer pageSize) {
        return ResponseEntity.ok(productService.getAllPublishForShop(pageNumber, pageSize));
    }

    @PostMapping("/draft/{product_id}")
    public void draftProductByShop(@PathVariable(value = "product_id") Long productId) {
        productService.draftProductByShop(productId);
    }

    @GetMapping("/draft/all")
    public ResponseEntity<?> draftProductByShop(@RequestParam(value = "page")
                                                @Min(value = 0, message = "Page index must not be less than zero")
                                                @NotNull(message = "Page index must not be null")
                                                Integer pageNumber,
                                                @RequestParam(value = "size")
                                                @Min(value = 0, message = "Page size must not be less than 1")
                                                @NotNull(message = "Page size must not be null")
                                                Integer pageSize) {
        return ResponseEntity.ok(productService.getAllDraftForShop(pageNumber, pageSize));
    }


    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }


    @GetMapping("/{catId}/books")
    public ResponseEntity<?> getBooksByCategory(@PathVariable Long catId, @RequestParam(value = "page")
                                                    @Min(value = 0, message = "Page index must not be less than zero")
                                                    @NotNull(message = "Page index must not be null")
                                                    Integer pageNumber,
                                                    @RequestParam(value = "size")
                                                    @Min(value = 0, message = "Page size must not be less than 1")
                                                    @NotNull(message = "Page size must not be null")
                                                    Integer pageSize) {
        return ResponseEntity.ok(productService.getProductsByCategory(catId, pageNumber, pageSize));
    }

    /**
     * GET /api/v1/product/{id}/related?limit=8
     * Public — returns related products in the same category
     */
    @GetMapping("/{id}/related")
    public ResponseEntity<List<ProductSummaryDTO>> getRelatedProducts(
            @PathVariable @NotNull @Min(1) Long id,
            @RequestParam(value = "limit", defaultValue = "8")
            @Min(value = 1, message = "Limit must be at least 1") int limit) {
        return ResponseEntity.ok(productService.getRelatedProducts(id, limit));
    }
}

