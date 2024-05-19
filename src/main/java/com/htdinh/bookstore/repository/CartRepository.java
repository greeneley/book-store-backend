package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Cart;
import com.htdinh.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findCartByUser(User user); 
}
