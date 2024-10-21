package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Attribute;
import com.htdinh.bookstore.model.AttributeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeItemRepository extends JpaRepository<AttributeItem, Long> {
    List<AttributeItem> findAllByAttribute(Attribute attribute);

    Optional<AttributeItem> findByName(String name);
}
