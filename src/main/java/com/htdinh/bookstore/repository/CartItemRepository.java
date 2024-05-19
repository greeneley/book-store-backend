package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Cart;
import com.htdinh.bookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByCart(Cart cart);
}
