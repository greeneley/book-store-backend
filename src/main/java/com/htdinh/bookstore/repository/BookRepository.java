package com.htdinh.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.htdinh.bookstore.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

//	@Query(value = "SELECT * FROM books WHERE name LIKE %:term%", nativeQuery = true)
//	List<Book> findByNameContaining(@Param("term") String term);

	List<Book> findAll();
	Book findById(int id);
	
	@Query(value="SELECT * FROM books WHERE books.FAVORITE = 'Y'", nativeQuery = true)
	List<Book> findAllFavoriteBooks();
}
