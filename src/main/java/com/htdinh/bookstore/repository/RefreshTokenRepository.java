package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByValue(String value);

    void deleteByUserId(Long userId);

    void deleteByValue(String value);

    boolean existsByValue(String value);

    @Query(value = "SELECT rt FROM RefreshToken rt WHERE rt.value = :value")
    Optional<RefreshToken> findByValueForUpdate(@Param("value") String value);
}
