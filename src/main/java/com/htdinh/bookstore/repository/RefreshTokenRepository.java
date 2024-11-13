package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByValue(String value);

    @Modifying
    @Query(value = "DELETE FROM REFRESH_TOKEN WHERE USER_ID = :userId", nativeQuery = true)
    void deleteByUserId(Long userId);
}
