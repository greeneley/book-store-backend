package com.htdinh.bookstore.repository;

import com.htdinh.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value="SELECT * FROM users WHERE users.USERNAME = :username and users.EMAIL = :email", nativeQuery = true)
    Optional<User> findByUser(String username, String email);
    
}
