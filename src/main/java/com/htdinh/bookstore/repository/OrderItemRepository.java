package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<Book, Integer> {
}
