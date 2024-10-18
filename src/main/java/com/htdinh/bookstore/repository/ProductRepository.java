package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, BigDecimal> {
    @Query(value = "select * from PRODUCT P order by rand(:seed)", nativeQuery = true)
    Page<Product> findAllProduct(Integer seed, Pageable pageable);
}
