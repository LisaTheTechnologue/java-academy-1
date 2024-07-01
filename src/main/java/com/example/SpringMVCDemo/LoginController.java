package com.example.SpringMVCDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    // username password form
    // login -> greeting
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
