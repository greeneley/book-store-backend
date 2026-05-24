package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.response.WishlistItemResponse;
import com.htdinh.bookstore.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    /**
     * GET /api/v1/wishlist
     * Authenticated — get current user's wishlist
     */
    @GetMapping
    public ResponseEntity<List<WishlistItemResponse>> getWishlist() {
        return ResponseEntity.ok(wishlistService.getWishlist());
    }

    /**
     * POST /api/v1/wishlist/{productId}
     * Authenticated — add product to wishlist
     */
    @PostMapping("/{productId}")
    public ResponseEntity<Void> addToWishlist(
            @PathVariable @NotNull @Min(1) Long productId) {
        wishlistService.addToWishlist(productId);
        return ResponseEntity.ok().build();
    }

    /**
     * DELETE /api/v1/wishlist/{productId}
     * Authenticated — remove product from wishlist
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromWishlist(
            @PathVariable @NotNull @Min(1) Long productId) {
        wishlistService.removeFromWishlist(productId);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/v1/wishlist/check/{productId}
     * Authenticated — check if product is in wishlist
     */
    @GetMapping("/check/{productId}")
    public ResponseEntity<Map<String, Boolean>> isInWishlist(
            @PathVariable @NotNull @Min(1) Long productId) {
        boolean inWishlist = wishlistService.isInWishlist(productId);
        return ResponseEntity.ok(Map.of("inWishlist", inWishlist));
    }
}

