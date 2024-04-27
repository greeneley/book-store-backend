package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.BookResponse;
import com.htdinh.bookstore.dto.CategoryResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.mapper.CategoryMapper;
import com.htdinh.bookstore.mapper.Mapper;
import com.htdinh.bookstore.model.Book;
import com.htdinh.bookstore.model.Category;
import com.htdinh.bookstore.repository.BookRepository;
import com.htdinh.bookstore.repository.CategoryRepository;
import com.htdinh.bookstore.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	public BookServiceImpl(BookRepository bookRepository,
						   CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public BookResponse getBook(Integer id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with ID = " + id + " not found"));
		
		int categoryId = book.getCategory().getCategoryId();
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with ID = " + categoryId + " not found"));
		
		
		
		return Mapper.toBookResponse(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with ID = " + id + " not found")));
	}

	@Override
	public Page<BookResponse> getAllBooks(int pageNumber, int pageSize, Integer seed) {
		return bookRepository.findRandomBooks(seed, PageRequest.of(pageNumber, pageSize)).map(Mapper::toBookResponse);
	}

	@Override
	public Page<BookResponse> getAllFavoriteBooks(int pageNumber, int pageSize, Integer seed) {
		return bookRepository.findAllFavoriteBooks(seed, PageRequest.of(pageNumber, pageSize)).map(Mapper::toBookResponse);
	}
	
}
