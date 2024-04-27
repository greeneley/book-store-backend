package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query(value = "select * from books b order by rand(:seed)", nativeQuery = true)
	Page<Book> findRandomBooks(Integer seed, Pageable pageable);
	@Query(value="SELECT * FROM books WHERE books.FAVORITE = 'Y' order by rand(:seed)", nativeQuery = true)
	Page<Book> findAllFavoriteBooks(Integer seed, Pageable pageable);
}
