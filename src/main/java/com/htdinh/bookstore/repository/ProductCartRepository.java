package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.ProductCart;
import com.htdinh.bookstore.model.ProductCartId;
import com.htdinh.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCartRepository extends JpaRepository<ProductCart, ProductCartId> {
    List<ProductCart> findAllByUser(User user);
}
