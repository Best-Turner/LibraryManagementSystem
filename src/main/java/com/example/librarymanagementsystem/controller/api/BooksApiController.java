package com.example.librarymanagementsystem.controller.api;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/books", produces = "application/json")
public class BooksApiController {

    private final BookRepository repository;

    @Autowired
    public BooksApiController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Book> books() {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> bookById(@PathVariable("id") long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        Book savedBook = repository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedBook);
    }

    @GetMapping("/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Book> booksByAuthorId(@PathVariable("id") long id) {
        return repository.findBookByAuthorId(id).orElse(Collections.emptyList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable("id") long id) {
        Optional<Book> byId = repository.findById(id);
        if (byId.isPresent()) {
            repository.delete(byId.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Книга с ID = %d не найдена", id));
        }
    }
}