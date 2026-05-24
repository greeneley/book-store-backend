package com.htdinh.bookstore.service;

import com.htdinh.bookstore.dto.response.WishlistItemResponse;

import java.util.List;

public interface WishlistService {

    void addToWishlist(Long productId);

    void removeFromWishlist(Long productId);

    List<WishlistItemResponse> getWishlist();

    boolean isInWishlist(Long productId);
}

