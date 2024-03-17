package com.quizSystem.repository;

import com.quizSystem.entity.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String> {
    Admin findByEmail(String email);
}