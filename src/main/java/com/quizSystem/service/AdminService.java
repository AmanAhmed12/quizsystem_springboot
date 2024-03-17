package com.quizSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quizSystem.entity.Admin;
import com.quizSystem.repository.AdminRepo;

@Service
public class AdminService {
@Autowired
    private AdminRepo adminRepo;

    public Admin findAdminByEmail(String email) {
        return adminRepo.findById(email).orElse(null);
    }

    public void saveOrUpdate(Admin admin) {
        adminRepo.save(admin);
    }

}
