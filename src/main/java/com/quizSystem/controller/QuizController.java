
package com.quizSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuizController {
    
    
    @GetMapping("/")
    public String home(){
        return "home";
    }
    
    @GetMapping("/log")
    public String login(){
        return "login";
    }
    
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    
    @GetMapping("/forgotpwd")
    public String forgotpassword(){
        return "forgotpassword";
    }
    
    @GetMapping("/defaultAdminDashContent")
    public String defaultAdminDashContent(){
        return "defaultAdminDashContent";
    }
    
    @GetMapping("/admindashboard")
    public String admindashboard(){
        return "admindashboard";
    }
    
    @GetMapping("/adminCreateAccount")
    public String adminCreateAccount(){
        return "adminAccountCreate";
    }
    
    @GetMapping("/ProfileUpdate")
    public String ProfileUpdate(){
        return "ProfileUpdate";
    }
    
    @GetMapping("/generateQuiz")
    public String generateQuiz(){
        return "generateQuiz";
    }
}
