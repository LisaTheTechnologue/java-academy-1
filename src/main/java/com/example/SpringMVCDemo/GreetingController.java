package com.example.SpringMVCDemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    @GetMapping("/greetings")
    public String greetings(Model model) {
        model.addAttribute("greetings", Greeting.readGreetings());
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greetings")
    public String create(@ModelAttribute("greeting") Greeting greeting) {
        Greeting.createGreeting(greeting);
        return "redirect:/greetings";
    }

    @GetMapping("/greetings/update/{id}")
    public String editForm(@PathVariable("id") int id,
                           Model model) {
        Greeting greeting1 = Greeting.getGreetingById(id);
        if (greeting1 != null) {
            model.addAttribute("greeting", greeting1);
        } else {
            model.addAttribute("error", "invalid id");
            return "greeting";
        }
        return "greeting_edit";
    }

    @PostMapping("/greetings/update/{id}")
    public String updateGreeting(@PathVariable("id") int id,
                                 @ModelAttribute("greeting") Greeting greeting,
                                 Model model) {
        if (!Greeting.updateGreeting(id,greeting)) {
            model.addAttribute("error", "invalid id");
            return "redirect:/greetings/update/{id}";
        }
        return "redirect:/greetings"; // Handle update failure (e.g., invalid ID)
    }

    @GetMapping("/greetings/delete/{id}")
    public String deleteGreeting(@PathVariable("id") int id,
                                 Model model) {
        if (!Greeting.deleteGreeting(id)) {
            model.addAttribute("error", "invalid id");
        }
        return "redirect:/greetings"; // Handle deletion failure (e.g., invalid ID)
    }
}
