package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.ReviewRequest;
import com.htdinh.bookstore.dto.response.ReviewResponse;
import com.htdinh.bookstore.exception.BusinessValidationException;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.model.Review;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.repository.ReviewRepository;
import com.htdinh.bookstore.service.ReviewService;
import com.htdinh.bookstore.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public ReviewResponse createReview(ReviewRequest request) {
        User currentUser = AuthUtils.getCurrentUser();

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + request.getProductId()));

        if (reviewRepository.existsByProductIdAndUserId(request.getProductId(), currentUser.getId())) {
            throw new BusinessValidationException("You have already reviewed this product");
        }

        Review review = Review.builder()
                .product(product)
                .user(currentUser)
                .rating(request.getRating())
                .title(request.getTitle())
                .body(request.getBody())
                .verifiedPurchase(false)
                .crtDt(LocalDateTime.now())
                .updtDt(LocalDateTime.now())
                .build();

        Review saved = reviewRepository.save(review);
        return toResponse(saved);
    }

    @Override
    public Page<ReviewResponse> getProductReviews(Long productId, int pageNumber, int pageSize) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        return reviewRepository.findByProductIdOrderByCrtDtDesc(productId, PageRequest.of(pageNumber, pageSize))
                .map(this::toResponse);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        User currentUser = AuthUtils.getCurrentUser();
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + reviewId));

        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new BusinessValidationException("You are not authorized to delete this review");
        }
        reviewRepository.delete(review);
    }

    @Override
    public Double getAverageRating(Long productId) {
        return reviewRepository.findAverageRatingByProductId(productId);
    }

    @Override
    public long getReviewCount(Long productId) {
        return reviewRepository.countByProductId(productId);
    }

    private ReviewResponse toResponse(Review review) {
        User u = review.getUser();
        ReviewResponse.ReviewUserDto userDto = ReviewResponse.ReviewUserDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .photos(u.getPhotos())
                .build();

        return ReviewResponse.builder()
                .id(review.getId())
                .rating(review.getRating())
                .title(review.getTitle())
                .body(review.getBody())
                .verifiedPurchase(review.getVerifiedPurchase())
                .crtDt(review.getCrtDt())
                .user(userDto)
                .build();
    }
}

