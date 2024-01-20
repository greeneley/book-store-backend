package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends CrudRepository<Customer, Long> {

}
