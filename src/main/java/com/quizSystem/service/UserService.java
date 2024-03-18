package com.quizSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quizSystem.entity.User;
import com.quizSystem.repository.UserRepo;



@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User findUserByIndex(String indexNo) {
        return userRepo.findByIndexNo(indexNo);
    }

    public void saveOrUpdate(User user) {
        userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll(); // Assuming you have a method in UserRepository to fetch all users
    }
}
