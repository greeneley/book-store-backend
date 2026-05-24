package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.request.ReviewRequest;
import com.htdinh.bookstore.dto.response.ReviewResponse;
import org.springframework.data.domain.Page;

public interface ReviewService {

    ReviewResponse createReview(ReviewRequest request);

    Page<ReviewResponse> getProductReviews(Long productId, int pageNumber, int pageSize);

    void deleteReview(Long reviewId);

    Double getAverageRating(Long productId);

    long getReviewCount(Long productId);
}

