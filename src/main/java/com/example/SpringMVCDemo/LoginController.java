package com.example.SpringMVCDemo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private static final User VALID_USER = new User("admin", "password"); // Replace with hardcoded or other mechanism

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User()); // Empty object for login form
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginForm(@ModelAttribute("user") User user,Model model, HttpSession session) {
//        User user = (User) model.getAttribute("user"); // Retrieve user from model
        if (user.getUsername().equals(VALID_USER.getUsername()) && user.getPassword().equals(VALID_USER.getPassword())) {
            // Login successful
            session.setAttribute("loggedIn", true); // Set session attribute for login status
            return "redirect:/greetings"; // Redirect to greetings after successful login
        } else {
            // Login failed
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login"; // Return login page with error message
        }
    }
}
