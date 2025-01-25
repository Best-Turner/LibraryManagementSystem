package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
@Slf4j
@Controller
@RequestMapping("/books")
@SessionAttributes(names = "savedBook")
public class BookController {

    private final BookRepository bookRepository;
    private  final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @GetMapping
    public String showBooks(@RequestParam(value = "save", required = false) String value, Model model) {
        if (value != null) {
            return "saveBook";
        }
        model.addAttribute("books", bookRepository.findAll());
        return "booksPage";
    }


    @ModelAttribute
    public void addGenreToModel(Model model) {
        model.addAttribute("genres", Book.Genre.values());
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable long id, Model model) {
        Book book = bookRepository.findById(id).get();
        model.addAttribute("book", book);
        return "bookDetails";
    }

    @PostMapping
    public String processBook(@ModelAttribute Book book, SessionStatus status) {
        System.out.println(book);
        System.out.println("Книга сохранена");
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("/appoint")
    public String authors(Model model) {
        model.addAttribute("own", new Author());
        model.addAttribute("authors", authorRepository.findAll());
        return "appointAuthor";
    }

    @PostMapping("/appoint")
    public String appointAuthors( @ModelAttribute("savedBook") Book book) {
        //System.out.println(author);
        //book.setAuthor(Collections.singletonList(author));
        System.out.println(book);
        return "redirect:/books?save";
    }


    @ModelAttribute
    private void savedBook(Model model) {
        Book book = new Book();
        book.setTitle("Title");
        model.addAttribute("savedBook", book);
    }

}
