package com.example.librarymanagementsystem.web;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorByIdConverter implements Converter<String, Author> {


    private final AuthorRepository repository;

    @Autowired
    public AuthorByIdConverter(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author convert(String source) {
        long id = Long.parseLong(source);
        System.out.println("КОНВЕРТЕР СРАБОТАЛ");
        return repository.findById(id).orElse(null);
    }
}
