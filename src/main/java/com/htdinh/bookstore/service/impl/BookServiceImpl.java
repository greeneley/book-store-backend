package com.htdinh.bookstore.service.impl;

import java.util.Optional;

import com.htdinh.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import com.htdinh.bookstore.model.Book;

@Service
public class BookServiceImpl {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

//	public Page<Book> findPaginated(Pageable pageable, String term) {
//
//		return page(pageable, term);
//	}

//	private Page<Book> page(Pageable pageable, String term) {
//		int pageSize = pageable.getPageSize();
//		int currentPage = pageable.getPageNumber();
//		int startItem = currentPage * pageSize;
//
//		ArrayList<Book> books;
//		List<Book> list;
//
//		if (term == null) {
//			books = (ArrayList<Book>) bookRepository.findAll();
//		} else {
//			books = (ArrayList<Book>) bookRepository.findByNameContaining(term);
//		}
//
//		if (books.size() < startItem) {
//			list = Collections.emptyList();
//		} else {
//			int toIndex = Math.min(startItem + pageSize, books.size());
//			list = books.subList(startItem, toIndex);
//		}
//
//		Page<Book> bookPage = new PageImpl<Book>(list, PageRequest.of(currentPage, pageSize), books.size());
//
//		return bookPage;
//	}

	public void save(Book book) {
		bookRepository.save(book);
	}

	public Optional<Book> findBookById(Integer id) {
		Optional<Book> book = bookRepository.findById(id);
		return book;
	}
    

	public void delete(Integer id) {
		bookRepository.deleteById(id);
	}

}
