package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Book;
import com.htdinh.bookstore.model.Cart;
import com.htdinh.bookstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query(value="SELECT * FROM cart_items WHERE cart_items.CART_ID = :cartId", nativeQuery = true)
    List<CartItem> findAllByCartId(int cartId);
    
    Optional<CartItem> findByCartAndBook(Cart cart, Book book);
}
