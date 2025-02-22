package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository repository;

    @Autowired
    public RegisterController(UserRepository repository) {
        this.repository = repository;
    }


    @ModelAttribute("newUser")
    public User newUser() {
        return new User();
    }


    @GetMapping
    public String formRegister() {
        return "/register";
    }

    @PostMapping()
    public String saveNewUser(@ModelAttribute User user) {
        repository.save(user);
        return "redirect:/login";
    }
}
