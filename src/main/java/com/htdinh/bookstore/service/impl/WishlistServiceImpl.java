package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.response.WishlistItemResponse;
import com.htdinh.bookstore.exception.BusinessValidationException;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.model.Product;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.model.Wishlist;
import com.htdinh.bookstore.model.WishlistId;
import com.htdinh.bookstore.repository.ProductRepository;
import com.htdinh.bookstore.repository.WishlistRepository;
import com.htdinh.bookstore.service.WishlistService;
import com.htdinh.bookstore.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void addToWishlist(Long productId) {
        User currentUser = AuthUtils.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        if (wishlistRepository.existsByIdUserIdAndIdProductId(currentUser.getId(), productId)) {
            throw new BusinessValidationException("Product is already in your wishlist");
        }

        WishlistId wishlistId = new WishlistId(currentUser.getId(), productId);
        Wishlist wishlist = Wishlist.builder()
                .id(wishlistId)
                .user(currentUser)
                .product(product)
                .crtDt(LocalDateTime.now())
                .build();

        wishlistRepository.save(wishlist);
    }

    @Override
    @Transactional
    public void removeFromWishlist(Long productId) {
        User currentUser = AuthUtils.getCurrentUser();

        if (!wishlistRepository.existsByIdUserIdAndIdProductId(currentUser.getId(), productId)) {
            throw new ResourceNotFoundException("Product not found in wishlist");
        }

        wishlistRepository.deleteByIdUserIdAndIdProductId(currentUser.getId(), productId);
    }

    @Override
    public List<WishlistItemResponse> getWishlist() {
        User currentUser = AuthUtils.getCurrentUser();
        return wishlistRepository.findByIdUserId(currentUser.getId())
                .stream()
                .map(w -> toResponse(w.getProduct()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isInWishlist(Long productId) {
        User currentUser = AuthUtils.getCurrentUser();
        return wishlistRepository.existsByIdUserIdAndIdProductId(currentUser.getId(), productId);
    }

    private WishlistItemResponse toResponse(Product product) {
        String thumbnailUrl = product.getProductImages().stream()
                .filter(pi -> Boolean.TRUE.equals(pi.getImage().getIsThumbnail()))
                .findFirst()
                .map(pi -> pi.getImage().getUrl())
                .orElse(null);

        return WishlistItemResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .author(product.getAuthor())
                .regularPrice(product.getRegularPrice())
                .salePrice(product.getSalePrice())
                .thumbnailUrl(thumbnailUrl)
                .stock(product.getStock())
                .build();
    }
}

