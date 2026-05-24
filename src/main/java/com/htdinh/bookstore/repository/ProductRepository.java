package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * Find related products in the same category, excluding the current product.
     * Uses native query for performance with LIMIT support.
     */
    @Query(value = """
            SELECT DISTINCT p.* FROM PRODUCT p
            JOIN PRODUCT_CATEGORY pc ON p.PRODUCT_ID = pc.PRODUCT_ID
            WHERE pc.CAT_ID IN (
                SELECT pc2.CAT_ID FROM PRODUCT_CATEGORY pc2 WHERE pc2.PRODUCT_ID = :productId
            )
            AND p.PRODUCT_ID != :productId
            AND p.IS_PUBLISH = TRUE
            ORDER BY p.PRODUCT_ID DESC
            LIMIT :limitCount
            """, nativeQuery = true)
    List<Product> findRelatedProducts(@Param("productId") Long productId, @Param("limitCount") int limitCount);
}
