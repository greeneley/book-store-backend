package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.BookResponse;
import com.htdinh.bookstore.model.Book;

public class Mapper {
    public static BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .bookId(book.getBookId())
                .name(book.getName())
                .price(book.getPrice())
                .authors(book.getAuthors())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .publishedOn(book.getPublishedOn())
                .desc(book.getDesc())
                .imageUrl(book.getImageUrl())
                .favorite(book.getFavorite())
                .build();
    }
}
