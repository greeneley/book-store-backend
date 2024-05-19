package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @JsonProperty("user_id")
    private int id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;

    private Boolean enabled;
    private AuthoritiesResponse authority;
}
