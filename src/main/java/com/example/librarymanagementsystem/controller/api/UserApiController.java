package com.example.librarymanagementsystem.controller.api;

import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserApiController {

    private final UserRepository repository;

    @Autowired
    public UserApiController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> users() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> userById(@PathVariable("id") long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping(value = "/register", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user) {
        return repository.save(user);
    }
}
