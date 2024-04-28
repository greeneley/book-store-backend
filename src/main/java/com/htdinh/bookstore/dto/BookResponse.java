package com.htdinh.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    @JsonProperty("book_id")
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
    
    private CategoryResponse category;
}
