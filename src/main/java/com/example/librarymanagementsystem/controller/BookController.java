package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.web.converter.StringToLocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;

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
            return "book/saveBook";
        }
        model.addAttribute("books", bookRepository.findAll());
        return "book/booksPage";
    }


    @ModelAttribute
    public void addGenreToModel(Model model) {
        model.addAttribute("genres", Book.Genre.values());
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).get());
        return "book/bookDetails";
    }

    @PostMapping
    public String processBook(@ModelAttribute("savedBook") Book book,
                              SessionStatus status) {
        bookRepository.save(book);
        status.setComplete();
        return "redirect:/";
    }

    @GetMapping("/appoint")
    public String authors(Model model) {
        model.addAttribute("own", new Author());
        model.addAttribute("authors", authorRepository.findAll());
        return "book/appointAuthor";
    }

    @PostMapping("/appoint")
    public String appointAuthors(@ModelAttribute("savedBook") Book book, Model model) {
        model.addAttribute("savedBook", book);
        return "redirect:/books?save";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Book bookFromDB = bookRepository.findById(id).get();
        model.addAttribute("editBook", bookFromDB);
        model.addAttribute("savedBook", bookFromDB);
        System.out.println("Отобразили форму");
        return "book/edit";
    }

    @PatchMapping()
    public String editBook(@ModelAttribute Book updatedBook,
                           @SessionAttribute("savedBook") Book savedBook,
                           SessionStatus status) {
        if (!updatedBook.getAuthor().isEmpty()) {
            savedBook.setAuthor(updatedBook.getAuthor());
        }
        String newTitle = updatedBook.getTitle();
        if (newTitle != null && !newTitle.isEmpty()) {
            savedBook.setTitle(newTitle);
        }
        if (!updatedBook.getGenre().toString().isEmpty()) {
            savedBook.setGenre(updatedBook.getGenre());
        }
        if (updatedBook.getPageCount() != 0 && updatedBook.getPageCount() > 0) {
            savedBook.setPageCount(updatedBook.getPageCount());
        }
        if (!updatedBook.getPublicationDate().isEqual(LocalDate.parse(StringToLocalDateConverter.DATE_BY_DEFAULT))) {
            savedBook.setPublicationDate(updatedBook.getPublicationDate());
        }
        savedBook.setAvailability(updatedBook.isAvailability());

        bookRepository.save(savedBook);
        status.setComplete();
        return "redirect:/books/" + savedBook.getId();
    }
}
