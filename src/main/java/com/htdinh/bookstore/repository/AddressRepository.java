package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Address;
import com.htdinh.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
