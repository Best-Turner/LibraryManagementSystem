package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping
    public String showBooks(@RequestParam(value = "save", required = false) String value, Model model) {
        if (value != null) {
            model.addAttribute("book", new Book());
            model.addAttribute("genres", Book.Genre.values());
            return "saveBook";
        }
        model.addAttribute("books", bookRepository.findAll());
        return "booksPage";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        return "bookDetails";
    }

    @PostMapping
    public String processBook(@ModelAttribute Book book) {

        System.out.println(book);
        return "redirect:/books";
    }

}
