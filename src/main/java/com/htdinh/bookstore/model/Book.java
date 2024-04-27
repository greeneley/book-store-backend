package com.htdinh.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID", nullable = false)
	private int bookId;

	@Column(name = "NAME", nullable = false)
	@NotBlank(message = "{book.name.notBlank}")
	private String name;

	@Column(name = "PRICE", nullable = false)
	@NotNull(message = "{book.price.notBlank}")
	private BigDecimal price;

	@Column(name = "AUTHORS", nullable = false)
	@NotBlank(message = "{book.authors.notBlank}")
	private String authors;

	@Column(name = "ISBN", nullable = false)
	@NotBlank(message = "{book.isbn.notBlank}")
	@Pattern(regexp = "\\d{10}|\\d{13}", message = "{book.isbn.size}")
	private String isbn;

	@Column(name = "PUBLISHER", nullable = false)
	@NotBlank(message = "{book.publisher.notBlank}")
	private String publisher;

	@Column(name = "PUBLISHER_ON", nullable = false)
	@NotNull(message = "{book.date.notNull}")
	private String publishedOn;

	@Lob
	@Column(name = "`DESC`")
	private String desc;

	@Lob
	@Column(name = "IMAGE_URL")
	private String imageUrl;

	@Size(max = 1)
	@Column(name = "FAVORITE", length = 1)
	private String favorite;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@Override
	public String toString() {
		return "Book [id=" + bookId + ", name=" + name + ", price=" + price + ", authors=" + authors + ", isbn=" + isbn
				+ ", publisher=" + publisher + ", publishedOn=" + publishedOn + "]";
	}
}
