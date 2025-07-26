package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.dto.ProductSummaryDTO;
import com.htdinh.bookstore.model.Category;
import com.htdinh.bookstore.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findProductCategoriesByCat(Category cat);
    
    @Query("""
               SELECT new com.htdinh.bookstore.dto.ProductSummaryDTO(p.id, p.name, p.description, p.regularPrice, p.salePrice, pi.image.url) FROM Product p JOIN p.productCategories pc LEFT JOIN p.productImages pi WITH pi.image.isThumbnail = true WHERE pc.cat.id = :categoryId
            """)
    Page<ProductSummaryDTO> findProductSummariesByCategoryId(
            @Param("categoryId") Long catId,
            Pageable pageable
    );
}
