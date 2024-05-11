package com.htdinh.bookstore.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthoritiesResponse {

    private Long id;
    private String name;
}
