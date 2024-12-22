package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.ProfileUserResponse;
import com.htdinh.bookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileUserMapper {
    @Mapping(target = "birthday", source = "birthDay")
    ProfileUserResponse toProfileUser(User user);
}

