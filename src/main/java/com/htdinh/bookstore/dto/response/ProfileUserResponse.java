package com.htdinh.bookstore.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String birthday;
    private String photos;
}
