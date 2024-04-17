package com.htdinh.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	@NotBlank(message = "{book.name.notBlank}")
	private String name;

	@Column(name = "price", nullable = false)
	@NotNull(message = "{book.price.notBlank}")
	private BigDecimal price;

	@Column(name = "authors", nullable = false)
	@NotBlank(message = "{book.authors.notBlank}")
	private String authors;

	@Column(name = "isbn", nullable = false)
	@NotBlank(message = "{book.isbn.notBlank}")
	@Pattern(regexp = "\\d{10}|\\d{13}", message = "{book.isbn.size}")
	private String isbn;

	@Column(name = "publisher", nullable = false)
	@NotBlank(message = "{book.publisher.notBlank}")
	private String publisher;

	@Column(name = "published_on", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{book.date.notNull}")
	private LocalDate publishedOn;
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", authors=" + authors + ", isbn=" + isbn
				+ ", publisher=" + publisher + ", publishedOn=" + publishedOn + "]";
	}

}
