package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Category;
import com.htdinh.bookstore.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findProductCategoriesByCat(Category cat);
}
