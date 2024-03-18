// UserController.java
package com.quizSystem.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.quizSystem.entity.Admin;
import com.quizSystem.entity.User;
import com.quizSystem.service.AdminService;
import com.quizSystem.service.UserService;

@Controller
public class UserController {

     
   

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/log")
    public String login(Model model) {
    
        return "login";
    }

    @GetMapping("/register")
    public String register() {
       
        return "register";
    }


    @GetMapping("/forgotpwd")
    public String forgotpassword() {
        return "forgotpassword";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/loadGuidelines")
    public String loadGuidelines() {
        return "guidelines";
    }

    @GetMapping("/defaultAdminDashContent")
    public String defaultAdminDashContent() {
        return "defaultAdminDashContent";
    }

    @GetMapping("/defaultStudentDashContent")
    public String defaultStudentDashContent() {
        return "defaultStudentDashContent";
    }

    @GetMapping("/admindashboard")
    public String admindashboard() {
        return "admindashboard";
    }

    @GetMapping("/studentdashboard")
    public String studentdashboard() {
        return "studentdashboard";
    }

    @GetMapping("/adminCreateAccount")
    public String adminCreateAccount() {
        return "adminAccountCreate";
    }

    @GetMapping("/ProfileUpdate")
    public String ProfileUpdate() {
        return "ProfileUpdate";
    }

    @GetMapping("/generateQuiz")
    public String generateQuiz() {
        return "generateQuiz";
    }

    @GetMapping("/manageQuiz")
    public String manageQuiz() {
        return "manageQuiz";
    }

    @GetMapping("/manageUser")
    public String manageUser() {
        return "manageUser";
    }

    @GetMapping("/logOut")
    public String logOut() {
        return "logout";
    }

    

    @PostMapping("/userLogin")
public String loginUser(@ModelAttribute("user") User user, HttpSession session,
        RedirectAttributes redirectAttributes) {
    String email = user.getEmail();

    // Check if the user exists in the student_details table
    User student = userService.findUserByEmail(email);

    // If not found in student_details, check in admin_details table
    if (student == null) {
        Admin adminData = adminService.findAdminByEmail(email);
        if (adminData != null && user.getPassword().equals(adminData.getPassword())
                && adminData.getStatus().equals("active")) {
            adminData.setStatus("loggedin");
            adminService.saveOrUpdate(adminData);
            session.setAttribute("loggedInUser", adminData); // Store admin data in session
            return "redirect:/admindashboard"; // Redirect to admin dashboard
        } else {
            // Add error message for incorrect credentials
            redirectAttributes.addFlashAttribute("error", "Incorrect email or password.");
            return "redirect:/log"; // Redirect to login page
        }
    } else {
        // User found in student_details, check password and status
        if (student.getPassword().equals(user.getPassword()) && student.getStatus().equals("active")) {
            student.setStatus("loggedin");
            userService.saveOrUpdate(student);
            session.setAttribute("loggedInUser", student); // Store student data in session
            return "redirect:/studentdashboard"; // Redirect to student dashboard
        } else {
            // Add error message for incorrect credentials or inactive status
            redirectAttributes.addFlashAttribute("error", "Incorrect email or password.");
            return "redirect:/log"; // Redirect to login page
        }
    }
}

    @PostMapping("/userLogout")
    public String logoutUser(@RequestParam(name = "confirmed", required = false) String confirmed,
            HttpSession session, RedirectAttributes redirectAttributes) {
        // If the user clicks the "Confirm" button
        if ("yes".equals(confirmed)) {
            // Retrieve the logged-in user from the session
            Object loggedInUser = session.getAttribute("loggedInUser");

            // If the logged-in user is a User
            if (loggedInUser instanceof User) {
                User loggedUser = (User) loggedInUser;
                loggedUser.setStatus("active");
                userService.saveOrUpdate(loggedUser);
            }

            // If the logged-in user is an Admin
            else if (loggedInUser instanceof Admin) {
                Admin loggedInAdmin = (Admin) loggedInUser;
                loggedInAdmin.setStatus("active");
                adminService.saveOrUpdate(loggedInAdmin);
            }

            // Invalidate the session to log out the user/admin
            session.invalidate();

            redirectAttributes.addFlashAttribute("success", "LoggedOut Sucessfully !!");
            // Redirect to login page after logout
            return "redirect:/log";
        } else {
            // Redirect to relevant dashboard based on user type
            Object loggedInUser = session.getAttribute("loggedInUser");
            if (loggedInUser instanceof User) {
                return "redirect:/studentdashboard";
            } else if (loggedInUser instanceof Admin) {
                return "redirect:/admindashboard";
            } else {
                return "redirect:/log"; // Redirect to login page if no user logged in
            }
        }
    }


   

@PostMapping("/forgotpassword")
public String passwordforgot(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("confirmpassword") String confirmPassword,
                             RedirectAttributes redirectAttributes) {
    
    // Check if the email exists in the admin_details table
    Admin admin = adminService.findAdminByEmail(email);
    if (admin != null) {
        // Update the password if the confirmation matches
        if (password.equals(confirmPassword)) {
            admin.setPassword(password);
            adminService.saveOrUpdate(admin);
           
            redirectAttributes.addFlashAttribute("error", "Passwords updated successfully!!");
            return "redirect:/log"; // Redirect to login page after successful password change
        } else {
            // Add error message for password mismatch
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
            return "redirect:/forgotpwd"; // Redirect back to forgot password page
        }
    }
    
    // Check if the email exists in the student_details table
    User student = userService.findUserByEmail(email);
    if (student != null) {
        // Update the password if the confirmation matches
        if (password.equals(confirmPassword)) {
            student.setPassword(password);
            userService.saveOrUpdate(student);
           
            redirectAttributes.addFlashAttribute("error", "Passwords updated successfully!!");
            return "redirect:/log"; // Redirect to login page after successful password change
        } else {
            // Add error message for password mismatch
            redirectAttributes.addFlashAttribute("error", "Passwords do not match.");
            return "redirect:/forgotpwd"; // Redirect back to forgot password page
        }
    }

    // If the email is not found in either table, show error message
    redirectAttributes.addFlashAttribute("error", "Invalid email.");
    return "redirect:/forgotpwd"; // Redirect back to forgot password page
}

@PostMapping("/studentRegistration")
public String studentRegistration(User user, String confirmpassword, RedirectAttributes redirectAttributes) {
    try {
        String password = user.getPassword();

        // Validate password matching
        if (!password.equals(confirmpassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match!");
            return "redirect:/register";
        }

        // Check if a user with the same email already exists
        User existingUserByEmail = userService.findUserByEmail(user.getEmail());
        if (existingUserByEmail != null) {
            redirectAttributes.addFlashAttribute("error", "Email is already registered!");
            return "redirect:/register";
        }

        // Check if a user with the same index number already exists
        User existingUserByIndexNo = userService.findUserByIndex(user.getIndexNo());
        if (existingUserByIndexNo != null) {
            redirectAttributes.addFlashAttribute("error", "Index number is already registered!");
            return "redirect:/register";
        }

        // Set default status for newly registered users
        user.setStatus("active");

        // Save the user object to the database using userService
        userService.saveOrUpdate(user);

        // Add a success message
        redirectAttributes.addFlashAttribute("success", "Registration successful!");

        // Redirect to login page after successful registration
        return "redirect:/log";
    } catch (Exception e) {
        e.printStackTrace();
        // Handle any exceptions that might occur during registration
        redirectAttributes.addFlashAttribute("error", "Registration failed. Please try again.");
        return "redirect:/register";
    }
}


  
}
