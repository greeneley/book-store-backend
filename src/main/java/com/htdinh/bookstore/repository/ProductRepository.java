package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from PRODUCT P WHERE P.USER_ID = :userId  order by rand(:seed)", nativeQuery = true)
    Page<Product> findAllById(Long userId, Integer seed, Pageable pageable);

    @Query(value = "select * from PRODUCT p WHERE p.name like %:name% and p.IS_PUBLISH IS TRUE", nativeQuery = true)
    Page<Product> findAllByName(String name, Pageable pageable);


    @Query(value = "select * from PRODUCT P WHERE P.USER_ID = :userId  and P.IS_PUBLISH IS TRUE", nativeQuery = true)
    Page<Product> findAllPublishProduct(Long userId, Pageable pageable);

    @Query(value = "select * from PRODUCT P WHERE P.USER_ID = :userId  and P.IS_PUBLISH IS FALSE", nativeQuery = true)
    Page<Product> findAllDraftProduct(Long userId, Pageable pageable);
}
