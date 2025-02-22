package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository repository;

    @Autowired
    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAuthors(Model model) {
        model.addAttribute("authors", repository.findAll());
        return "author/authors";
    }

    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable("id") long id, Model model) {
        Author author = repository.findById(id)
                .orElse(new Author(0, "<unknown>", "<unknown>", Collections.emptyList()));
        model.addAttribute("author", author);
        return "author/authorById";
    }

    @GetMapping("/save")
    public String saveAuthor(Model model) {
        model.addAttribute("newAuthor", new Author());
        return "author/newAuthor";
    }

    @PostMapping
    public String processSaveAuthor(@ModelAttribute Author author) {
        repository.save(author);
        return "redirect:/authors";
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/authors";
    }
}
