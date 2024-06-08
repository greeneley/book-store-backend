package com.htdinh.bookstore.mapper;

import com.htdinh.bookstore.dto.response.AddressResponse;
import com.htdinh.bookstore.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResponse toAddressResponse(Address address);
}
