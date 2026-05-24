package com.htdinh.bookstore.controller;

import com.htdinh.bookstore.dto.request.ReviewRequest;
import com.htdinh.bookstore.dto.response.ReviewResponse;
import com.htdinh.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * GET /api/v1/product/{productId}/reviews
     * Public endpoint — paginated reviews for a product
     */
    @GetMapping("/product/{productId}/reviews")
    public ResponseEntity<Page<ReviewResponse>> getProductReviews(
            @PathVariable @NotNull @Min(1) Long productId,
            @RequestParam(value = "page", defaultValue = "0")
            @Min(value = 0, message = "Page index must not be less than zero") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10")
            @Min(value = 1, message = "Page size must not be less than 1") Integer pageSize) {
        return ResponseEntity.ok(reviewService.getProductReviews(productId, pageNumber, pageSize));
    }

    /**
     * POST /api/v1/reviews
     * Authenticated — submit a review
     */
    @PostMapping("/reviews")
    public ResponseEntity<ReviewResponse> createReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(request));
    }

    /**
     * DELETE /api/v1/reviews/{reviewId}
     * Authenticated — delete own review
     */
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable @NotNull @Min(1) Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}

