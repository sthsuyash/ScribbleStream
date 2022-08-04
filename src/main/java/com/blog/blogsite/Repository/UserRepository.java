package com.blog.blogsite.Repository;

import com.blog.blogsite.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // SELECT * FROM user WHERE username=?
    @Query("SELECT u FROM User u WHERE u.username=?1") // JPQL
    Optional<Object> findByUsername(String username);
}
