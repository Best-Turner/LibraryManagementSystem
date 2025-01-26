package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/books")
@SessionAttributes(names = "savedBook")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @GetMapping
    public String showBooks(@RequestParam(value = "save", required = false) String value,
                            @SessionAttribute(value = "savedBook", required = false) Book book,
                            Model model) {
        if (value != null) {
            if (book == null) {
                book = new Book();
                model.addAttribute("savedBook", book);
            }
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
        model.addAttribute("book", bookRepository.findById(id).get());
        return "bookDetails";
    }

    @PostMapping
    public String processBook(@ModelAttribute("savedBook") Book book,
                              SessionStatus status) {
        System.out.println(book);
        bookRepository.save(book);
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
    public String appointAuthors(@ModelAttribute("savedBook") Book book, Model model) {
        model.addAttribute("savedBook", book);
        return "redirect:/books?save";
    }
}
