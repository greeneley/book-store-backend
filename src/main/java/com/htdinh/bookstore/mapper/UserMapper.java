package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.UserResponse;
import com.htdinh.bookstore.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
