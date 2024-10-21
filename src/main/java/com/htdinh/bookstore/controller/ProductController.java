package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.response.ProductResponse;
import com.htdinh.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    public void publishProductByShop(@PathVariable(value = "product_id") BigDecimal productId) {
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
    public void draftProductByShop(@PathVariable(value = "product_id") BigDecimal productId) {
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


//    @PostMapping("")
//    public ResponseEntity<?> createProduct(@RequestParam(value = "name") String name,
//                                           @RequestParam(value = "description") String description,
//                                           @RequestParam(value = "stock") int stock,
//                                           @RequestParam(value = "price") BigDecimal price,
//                                           @RequestParam(value = "category") String category,
//                                           @RequestParam(value = "variant") String variant,
//                                           @RequestParam(value = "image") String image) {
//        // Check if product category exists, if exists, add it
//        if (productService.categoryExists(category)) {
//            productService.addCategoryToProduct(name, category);
//        }
//
//        // Check if product variant exists, if exists, add it
//        if (productService.variantExists(variant)) {
//            productService.addVariantToProduct(name, variant);
//        }
//
//        // Check if product image exists, if exists, add it
//        if (productService.imageExists(image)) {
//            productService.addImageToProduct(name, image);
//        }
//
//        // Create the product
//        ProductResponse productResponse = productService.createProduct(name, description, stock, price);
//
//        return ResponseEntity.ok(productResponse);
//    }


}