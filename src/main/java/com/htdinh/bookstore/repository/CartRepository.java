package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Cart;
import com.htdinh.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


    @Query(value = "SELECT * FROM Cart WHERE user_id = :userId", nativeQuery = true)
    Optional<Cart> findCartByUserId(int userId); 
}
