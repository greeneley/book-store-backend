package com.htdinh.bookstore.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import com.htdinh.bookstore.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

	ArrayList<Order> findByOrderDate(LocalDate term);
	
	ArrayList<Order> findOrdersById(Long id);

}
