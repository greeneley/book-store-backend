package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.AddressOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressOrderRepository extends JpaRepository<AddressOrder, Long> {

}
