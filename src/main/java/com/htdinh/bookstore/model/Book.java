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
import java.util.List;

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
	private Integer bookId;

	@Column(name = "NAME", nullable = false)
	@NotBlank(message = "{book.name.notBlank}")
	private String name;

	@Column(name = "PRICE", nullable = false)
	@NotNull(message = "{book.price.notBlank}")
	private BigDecimal   price;

	@Column(name = "AUTHOR", nullable = false)
	@NotBlank(message = "{book.author.notBlank}")
	private String author;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
	private List<CartItem> cartItemsList;
	
	@Override
	public String toString() {
		return "Book [id=" + bookId + ", name=" + name + ", price=" + price + ", author=" + author + ", isbn=" + isbn
				+ ", publisher=" + publisher + ", publishedOn=" + publishedOn + "]";
	}
}
