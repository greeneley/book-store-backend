package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.BookResponse;
import com.htdinh.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toBookResponse(Book book);
}
