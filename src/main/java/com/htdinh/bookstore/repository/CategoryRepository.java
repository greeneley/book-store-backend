package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query(
            value = """
                    WITH RECURSIVE PRNT_CAT_LIST AS (SELECT CAT.CAT_ID,
                                                            CAT.NAME,
                                                            CAT.SLUG,
                                                            CAST(NULL AS DECIMAL(22, 0))   AS "PARENT_CAT_ID",
                                                            CAT.CRT_ID,
                                                            CAT.CRT_DT,
                                                            CAT.UPDT_ID,
                                                            CAT.UPDT_DT
                                                     FROM CATEGORY CAT
                                                     WHERE CAT.PARENT_CAT_ID IS NULL
                                                     UNION ALL
                                                     SELECT CAT.CAT_ID,
                                                            CAT.NAME,
                                                            CAT.SLUG,
                                                            PARENT_CAT.CAT_ID                      AS "PARENT_CAT_ID",
                                                            CAT.CRT_ID,
                                                            CAT.CRT_DT,
                                                            CAT.UPDT_ID,
                                                            CAT.UPDT_DT
                                                     FROM CATEGORY CAT
                                                              JOIN PRNT_CAT_LIST PARENT_CAT ON PARENT_CAT.CAT_ID = CAT.PARENT_CAT_ID)
                    SELECT *
                    FROM PRNT_CAT_LIST
                    """, nativeQuery = true
    )
    List<Category> findAllCategoryHierarchy();

}
