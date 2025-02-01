package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/{id}")
    public String userById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "userById";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "saveUser";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get()); // Передаем пользователя в форму
            return "editUser"; // Возвращаем страницу редактирования
        } else {
            return "redirect:/users"; // Если пользователь не найден, перенаправляем на список пользователей
        }
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable("id") long id,
                             @Valid @ModelAttribute("user") User updatedUser,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "editUser"; // Если есть ошибки валидации, возвращаем форму с ошибками
        }

        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstname(updatedUser.getFirstname());
            user.setLastname(updatedUser.getLastname());
            user.setPassword(updatedUser.getPassword());
            user.setBirthday(updatedUser.getBirthday());
            user.setRole(updatedUser.getRole());
            userRepository.save(user);
        }
        return "redirect:/users";
    }
}
