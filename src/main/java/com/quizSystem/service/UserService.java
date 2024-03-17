package com.quizSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quizSystem.entity.User;
import com.quizSystem.repository.UserRepo;

@Service
public class UserService {
@Autowired
    private UserRepo userRepo;

    public User findUserByEmail(String email) {
        return userRepo.findById(email).orElse(null);
    }

    public void saveOrUpdate(User user) {
        userRepo.save(user);
    }
}