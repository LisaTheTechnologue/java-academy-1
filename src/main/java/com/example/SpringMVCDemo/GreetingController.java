package com.example.SpringMVCDemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {

    @GetMapping("/greetingDemo")
    public String greetingDemo() {
        return "greeting";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greetings")
    public String greetings(Model model) {
        List<Greeting> greetings1 = Greeting.readGreetings();
        model.addAttribute("greetings", greetings1);
        return "greeting_btvn2";
    }

    @GetMapping("/greeting-btvn3")
    public String greetingBTVN3(Model model) {
        model.addAttribute("greetings", Greeting.readGreetings());
        model.addAttribute("greeting", new Greeting());
        return "greeting_btvn3";
    }

    @PostMapping("/greeting-btvn3")
    public String create(@ModelAttribute("greeting") Greeting greeting) {
        Greeting.createGreeting(greeting);
        return "redirect:/greeting-btvn3";
    }

    @GetMapping("/greeting-btvn3/update/{id}")
    public String editForm(@PathVariable("id") int id,
                           Model model) {
        Greeting greeting1 = Greeting.getGreetingById(id);
        if (greeting1 != null) {
            model.addAttribute("greeting", greeting1);
        } else {
            model.addAttribute("error", "invalid id");
            return "greeting_btvn3";
        }
        return "greeting_btvn3_edit";
    }

    @PostMapping("/greeting-btvn3/update/{id}")
    public String updateGreeting(@PathVariable("id") int id,
                                 @ModelAttribute("greeting") Greeting greeting,
                                 Model model) {
        if (!Greeting.updateGreeting(id,greeting)) {
            model.addAttribute("error", "invalid id");
            return "redirect:/greeting-btvn3/update/{id}";
        }
        return "redirect:/greeting-btvn3"; // Handle update failure (e.g., invalid ID)
    }

    @GetMapping("/greeting-btvn3/delete/{id}")
    public String deleteGreeting(@PathVariable("id") int id,
                                 Model model) {
        if (!Greeting.deleteGreeting(id)) {
            model.addAttribute("error", "invalid id");
        }
        return "redirect:/greeting-btvn3"; // Handle deletion failure (e.g., invalid ID)
    }
}
