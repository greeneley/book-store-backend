package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
public class CustomerBooks {

	private Customer customer;
	private List<Book> books;

	public CustomerBooks(Customer customer, List<Book> books) {
		this.customer = customer;
		this.books = books;
	}
	
}