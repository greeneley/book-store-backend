package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.BookResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.mapper.BookStructMapper;
import com.htdinh.bookstore.repository.BookRepository;
import com.htdinh.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	
	@Autowired
	private BookStructMapper bookStructMapper;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public BookResponse getBook(Integer id) {
		return bookStructMapper.toBookResponse(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with ID = " + id + " not found")));
	}

	@Override
	public Page<BookResponse> getAllBooks(int pageNumber, int pageSize, Integer seed) {
		return bookRepository.findRandomBooks(seed, PageRequest.of(pageNumber, pageSize)).map(bookStructMapper::toBookResponse);
	}

	@Override
	public Page<BookResponse> getAllFavoriteBooks(int pageNumber, int pageSize, Integer seed) {
		return bookRepository.findAllFavoriteBooks(seed, PageRequest.of(pageNumber, pageSize)).map(bookStructMapper::toBookResponse);
	}
	
}
