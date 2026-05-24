package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Wishlist;
import com.htdinh.bookstore.model.WishlistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, WishlistId> {

    List<Wishlist> findByIdUserId(Long userId);

    boolean existsByIdUserIdAndIdProductId(Long userId, Long productId);

    void deleteByIdUserIdAndIdProductId(Long userId, Long productId);
}

