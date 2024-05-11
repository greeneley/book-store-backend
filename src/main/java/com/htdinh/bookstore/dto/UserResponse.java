package com.htdinh.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.htdinh.bookstore.model.Authorities;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    @JsonProperty("user_id")
    private int id;
    private String username;
    private String email;
    
    private Boolean enabled;
    private AuthoritiesResponse authority;
}
