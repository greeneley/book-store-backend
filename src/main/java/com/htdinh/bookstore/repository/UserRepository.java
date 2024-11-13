package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //    @Query(value = "SELECT * FROM USER WHERE USERNAME = :username", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM USER WHERE USERNAME = :username and EMAIL = :email", nativeQuery = true)
    Optional<User> findByUsernameAndEmail(String username, String email);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT MAX(USER_ID) FROM USER", nativeQuery = true)
    Long getMaxId();
}
