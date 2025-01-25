//package com.example.librarymanagementsystem.controller;
//
//import com.example.librarymanagementsystem.model.Author;
//import com.example.librarymanagementsystem.model.Book;
//import com.example.librarymanagementsystem.repository.AuthorRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collections;
//
//public class AuthorController {
//
////    private final AuthorRepository repository;
////
////    public AuthorController(AuthorRepository repository) {
////        this.repository = repository;
////    }
////
////    @GetMapping
////    public String authors(@RequestParam(value = "appoint", required = false) String appiont,
////                          @SessionAttribute("savedBook") Book book,
////                          Model model) {
////        model.addAttribute("savedBook", book);
////        model.addAttribute("authors", repository.findAll());
////        if (appiont != null) {
////            return "appointAuthor";
////        }
////        return "authors";
////    }
////
////    @PostMapping("/appoint")
////    public String appointAuthor(@RequestParam Author author, @SessionAttribute("savedBook") Book book) {
////        book.setAuthor(Collections.singletonList(author));
////        return "saveBook";
//    //}
//}
