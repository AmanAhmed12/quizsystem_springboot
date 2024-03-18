package com.quizSystem.repository;

import com.quizSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByIndexNo(@Param("indexNo") String indexNo);
}