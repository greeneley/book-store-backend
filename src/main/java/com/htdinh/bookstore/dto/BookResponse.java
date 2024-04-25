package com.htdinh.bookstore.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private int bookId;
    private String name;
    private BigDecimal price;
    private String authors;
    private String isbn;
    private String publisher;
    private String publishedOn;
    private String desc;
    private String imageUrl;
    private String favorite;
}
