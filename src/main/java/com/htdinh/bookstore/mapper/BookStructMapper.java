package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.BookResponse;
import com.htdinh.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookStructMapper {
    BookResponse toBookResponse(Book book);
}
